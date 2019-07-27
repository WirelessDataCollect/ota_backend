package com.ruili.fota.constant;


public enum MongoDBEnum {
    /**
     * GridFS存储筒
     */
    GridFSBucket_FIRMWARE("firmware");

    private String gridFSBucket;

    MongoDBEnum(String gridFSBucket) {
        this.gridFSBucket = gridFSBucket;
    }

    public String getGridFSBucket() {
        return gridFSBucket;
    }

    public void setGridFSBucket(String gridFSBucket) {
        this.gridFSBucket = gridFSBucket;
    }
}
