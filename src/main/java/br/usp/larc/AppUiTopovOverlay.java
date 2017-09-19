/*
 * Copyright 2016-present Open Networking Laboratory
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
package br.usp.larc;

import org.onlab.osgi.DefaultServiceDirectory;
import org.onosproject.net.DeviceId;
import org.onosproject.net.Host;
import org.onosproject.net.HostId;
import org.onosproject.net.host.HostService;
import org.onosproject.ui.GlyphConstants;
import org.onosproject.ui.UiTopoOverlay;
import org.onosproject.ui.topo.ButtonId;
import org.onosproject.ui.topo.PropertyPanel;
import org.onosproject.ui.topo.TopoConstants.CoreButtons;

import static org.onosproject.ui.topo.TopoConstants.Properties.*;

/**
 * Our topology overlay.
 */
public class AppUiTopovOverlay extends UiTopoOverlay {

    // NOTE: this must match the ID defined in sampleTopov.js
    private static final String OVERLAY_ID = "sdn-lab-overlay";

    private static final String MY_TITLE = "SDN Lab!";
    private static final String MY_VERSION = "Beta";
    private static final String MY_DEVICE_TITLE = "OVSwitch";

    private static final ButtonId ADD_HOST_BUTTON = new ButtonId("addHost");
    private static final ButtonId REMOVE_HOST_BUTTON = new ButtonId("removeHost");
    private HostService hostService;


    public AppUiTopovOverlay() {
        super(OVERLAY_ID);
        this.hostService = DefaultServiceDirectory.getService(HostService.class);
    }


    @Override
    public void modifySummary(PropertyPanel pp) {
        pp.title(MY_TITLE)
                .glyphId(GlyphConstants.CLOUD)
                .removeProps(
                        TOPOLOGY_SSCS,
                        TUNNELS,
                        FLOWS,
                        VERSION
                )
                .addProp(VERSION, MY_VERSION);
    }

    @Override
    public void modifyDeviceDetails(PropertyPanel pp, DeviceId deviceId) {
        pp.title(MY_DEVICE_TITLE);
        pp.removeProps(LATITUDE, LONGITUDE, TUNNELS);
        pp.removeAllButtons();
        pp.addButton(CoreButtons.SHOW_FLOW_VIEW).addButton(CoreButtons.SHOW_PORT_VIEW);

        pp.addButton(ADD_HOST_BUTTON);


    }

    @Override
    public void modifyHostDetails(PropertyPanel pp, HostId hostId) {
        Host host = hostService.getHost(hostId);
        pp.removeProps(LATITUDE, LONGITUDE, VLAN);
        pp.addButton(REMOVE_HOST_BUTTON);
        pp.addProp("Custom Prop", "prop value");
    }
}
