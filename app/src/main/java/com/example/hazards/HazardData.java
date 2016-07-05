package com.example.hazards;

public class HazardData {
    private String mSiteName;
    private String mFullName;
    private String mAddress;
    private String mCompany;
    private String mProjectId;
    private String mWorkType;
    private String mHazardName;
    private String mHazardDetails;

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public String getFullName() {
        return mFullName;
    }

    public void setFullName(String fullName) {
        mFullName = fullName;
    }

    public String getHazardDetails() {
        return mHazardDetails;
    }

    public void setHazardDetails(String hazardDetails) {
        mHazardDetails = hazardDetails;
    }

    public String getHazardName() {
        return mHazardName;
    }

    public void setHazardName(String hazardName) {
        mHazardName = hazardName;
    }

    public String getProjectId() {
        return mProjectId;
    }

    public void setProjectId(String projectId) {
        mProjectId = projectId;
    }

    public String getSiteName() {
        return mSiteName;
    }

    public void setSiteName(String siteName) {
        mSiteName = siteName;
    }

    public String getWorkType() {
        return mWorkType;
    }

    public void setWorkType(String workType) {
        mWorkType = workType;
    }
}
