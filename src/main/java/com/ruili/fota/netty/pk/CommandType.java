package com.ruili.fota.netty.pk;

public enum CommandType {
    RIGISTER("register"),
    RIGISTER_ACK("regrepok"),
    CONFIG("config"),
    CONFIG_ACK("configok"),
    REQUEST_PACK("reqpack"),
    REQUEST_PACK_START("packstart"),
    REQUEST_PACK_END("packend"),
    UPDATE_OK("updateok"),
    UPDATE_ERROR("updateerr");



    final String type;

    CommandType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
