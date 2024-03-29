package com.example.springbootpubsub.adapter.framework;

import akka.actor.Address;
import akka.cluster.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Akka Cluster Utils
 *
 * @author dmihalishin@gmail.com
 */
public class AkkaClusterUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AkkaClusterUtils.class);

    private static final String SEED_NODES_PROGRAMMATICALLY = "SEED_NODES_PROGRAMMATICALLY";

    private static final Pattern SEED_PATTERN = Pattern.compile("^(.*)://(.*)@(.*):(.*)$");

    public static void joinToCluster(final Cluster cluster,
                                     final Map<String, String> environments) {
        //index of seed nodes starts from 0
        int index = 0;
        String seed;

        final List<Address> availableSeeds = new ArrayList<>();

        // identifying seed nodes and finish while loop of there are no more seed hosts mentioned in configuration
        while ((seed = environments.get(SEED_NODES_PROGRAMMATICALLY + "." + index)) != null) {

            final Matcher matcher = SEED_PATTERN.matcher(seed);

            assert matcher.matches();

            //verify if the seed host can be resolved
            if (hostIsResolved(matcher.group(3))) {
                final Address address = new Address(matcher.group(1), matcher.group(2), matcher.group(3), Integer.valueOf(matcher.group(4)));
                availableSeeds.add(address);
            }
            index++;
        }

        if (availableSeeds.isEmpty()) {
            LOGGER.warn("NO seeds available, cannot join to the cluster. Working in standalone mode");
        } else {
            cluster.joinSeedNodes(availableSeeds);
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static boolean hostIsResolved(final String host) {
        try {
            InetAddress.getByName(host);
            return true;
        } catch (UnknownHostException e) {
            LOGGER.debug(e.getMessage(), e);
            return false;
        }
    }
}