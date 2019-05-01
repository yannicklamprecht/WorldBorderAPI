package com.astromc.borderapi.type;


public abstract class SupportedPacketPlayOutWorldBorder {

    private Object borderPacket;

    /**
     * Get a version-ambiguous world border packet.
     * @param packet - must be of type Packet<?>
     * @post borderPacket
     */

    public SupportedPacketPlayOutWorldBorder(Object packet) {
        this.borderPacket = packet;
    }

    /**
     * Get the ambiguous packet object.
     * @return a Packet<?> object
     */

    public Object getAnonymousPacket() {
        return borderPacket;
    }

}
