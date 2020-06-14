/*
 * Jicofo, the Jitsi Conference Focus.
 *
 * Copyright @ 2015 Atlassian Pty Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jitsi.jicofo.event;

import org.jitsi.eventadmin.*;
import org.jitsi.jicofo.bridge.*;
import org.jxmpp.jid.*;

import java.util.*;

/**
 * Class for JVB related events.
 *
 * @author Pawel Domas
 */
public class BridgeEvent
    extends Event
{
    /**
     * The event is triggered by {@link BridgeSelector} whenever new functional
     * bridge has been discovered.
     */
    public static final String BRIDGE_UP = "org/jitsi/jicofo/JVB/UP";

    /**
     * The event is triggered by {@link BridgeSelector} whenever a bridge goes
     * down(stops working or disconnects).
     */
    public static final String BRIDGE_DOWN = "org/jitsi/jicofo/JVB/DOWN";

    /**
     * The key for event property
     */
    private final static String JVB_JID_KEY = "bridge.jid";

    /**
     * Used to init the properties passed to the constructor.
     * @param bridgeJid
     */
    static private Dictionary<String, Object> initDictionary(Jid bridgeJid)
    {
        Dictionary<String, Object> props = new Hashtable<>();
        props.put(JVB_JID_KEY, bridgeJid);
        return props;
    }

    /**
     * Creates {@link #BRIDGE_UP} <tt>BridgeEvent</tt>.
     * @param bridgeJid the JID of the bridge for which the event will be
     *                  created.
     * @return {@link #BRIDGE_UP} <tt>BridgeEvent</tt> for given
     *         <tt>bridgeJid</tt>.
     */
    static public BridgeEvent createBridgeUp(Jid bridgeJid)
    {
        return new BridgeEvent(BRIDGE_UP, bridgeJid);
    }

    /**
     * Creates {@link #BRIDGE_DOWN} <tt>BridgeEvent</tt>.
     * @param bridgeJid the JID of the bridge for which the event will be
     *                  created.
     * @return {@link #BRIDGE_DOWN} <tt>BridgeEvent</tt> for given
     *         <tt>bridgeJid</tt>.
     */
    static public BridgeEvent createBridgeDown(Jid bridgeJid)
    {
        return new BridgeEvent(BRIDGE_DOWN, bridgeJid);
    }

    private BridgeEvent(String topic, Jid bridgeJid)
    {
        super(topic, initDictionary(bridgeJid));
    }

    /**
     * Gets bridge JID associated with this <tt>BridgeEvent</tt> instance.
     *
     * @return The JID of the jitsi-videobridge instance for this event.
     */
    public Jid getBridgeJid()
    {
        return (Jid) getProperty(JVB_JID_KEY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof BridgeEvent))
        {
            return false;
        }

        BridgeEvent other = (BridgeEvent) obj;
        // Verify topic and JID
        boolean sameTopicAndJid
            = getTopic().equals(other.getTopic()) &&
                    getBridgeJid().equals(other.getBridgeJid());

        return sameTopicAndJid;
    }
}
