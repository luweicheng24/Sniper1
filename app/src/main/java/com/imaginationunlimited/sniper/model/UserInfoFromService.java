package com.imaginationunlimited.sniper.model;

/**
 * Created by MSC on 2018/6/6.
 * BJ AlphaMobile
 */
public class UserInfoFromService {
    private static UserInfoFromService userInfoFromService;

    public static UserInfoFromService getInstance() {
        if (userInfoFromService == null) {
            userInfoFromService = new UserInfoFromService();
            return userInfoFromService;
        }
        return userInfoFromService;
    }

    private int result;
    private String token;
    private UserOfService user;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserOfService getUser() {
        return user;
    }

    public void setUser(UserOfService user) {
        this.user = user;
    }

    public class UserOfService {
        private String id;
        private String avatar;
        private String userPhoto;
        private String name;
        private String desc;
        private String instgramId;
        private String gender;
        private String imUsername;
        private String work;
        private String school;
        private String birthYear;
        private String longitude;
        private String latitude;
        private String city;
        private String district;
        private String distanceWithin;
        private String lookingFor;
        private String ageRangeStart;
        private String ageRangeEnd;
        private String newMatchesOn;
        private String messagesOn;
        private String superLikesOn;
        private String inAppVibrationsOn;
        private String isSmartPhotoOn;
        private String isInsPhotoOn;
        private String inAppSoundsOn;
        private String instgramToken;
        private String spotifyAccessToken;
        private String spotifyRefreshToken;
        private String isShowWorkOn;
        private String isShowSchoolOn;
        private String isShowGenderOn;
        private String showMe;
        private String shieldBadInfo;
        private String spotifyId;
        private String is_display;
        private String imPassword;
        private String score;
        private int isVip;
        private int isAddSpeed;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUserPhoto() {
            return userPhoto;
        }

        public void setUserPhoto(String userPhoto) {
            this.userPhoto = userPhoto;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getInstgramId() {
            return instgramId;
        }

        public void setInstgramId(String instgramId) {
            this.instgramId = instgramId;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getImUsername() {
            return imUsername;
        }

        public void setImUsername(String imUsername) {
            this.imUsername = imUsername;
        }

        public String getWork() {
            return work;
        }

        public void setWork(String work) {
            this.work = work;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getBirthYear() {
            return birthYear;
        }

        public void setBirthYear(String birthYear) {
            this.birthYear = birthYear;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getDistanceWithin() {
            return distanceWithin;
        }

        public void setDistanceWithin(String distanceWithin) {
            this.distanceWithin = distanceWithin;
        }

        public String getLookingFor() {
            return lookingFor;
        }

        public void setLookingFor(String lookingFor) {
            this.lookingFor = lookingFor;
        }

        public String getAgeRangeStart() {
            return ageRangeStart;
        }

        public void setAgeRangeStart(String ageRangeStart) {
            this.ageRangeStart = ageRangeStart;
        }

        public String getAgeRangeEnd() {
            return ageRangeEnd;
        }

        public void setAgeRangeEnd(String ageRangeEnd) {
            this.ageRangeEnd = ageRangeEnd;
        }

        public String getNewMatchesOn() {
            return newMatchesOn;
        }

        public void setNewMatchesOn(String newMatchesOn) {
            this.newMatchesOn = newMatchesOn;
        }

        public String getMessagesOn() {
            return messagesOn;
        }

        public void setMessagesOn(String messagesOn) {
            this.messagesOn = messagesOn;
        }

        public String getSuperLikesOn() {
            return superLikesOn;
        }

        public void setSuperLikesOn(String superLikesOn) {
            this.superLikesOn = superLikesOn;
        }

        public String getInAppVibrationsOn() {
            return inAppVibrationsOn;
        }

        public void setInAppVibrationsOn(String inAppVibrationsOn) {
            this.inAppVibrationsOn = inAppVibrationsOn;
        }

        public String getIsSmartPhotoOn() {
            return isSmartPhotoOn;
        }

        public void setIsSmartPhotoOn(String isSmartPhotoOn) {
            this.isSmartPhotoOn = isSmartPhotoOn;
        }

        public String getIsInsPhotoOn() {
            return isInsPhotoOn;
        }

        public void setIsInsPhotoOn(String isInsPhotoOn) {
            this.isInsPhotoOn = isInsPhotoOn;
        }

        public String getInAppSoundsOn() {
            return inAppSoundsOn;
        }

        public void setInAppSoundsOn(String inAppSoundsOn) {
            this.inAppSoundsOn = inAppSoundsOn;
        }

        public String getInstgramToken() {
            return instgramToken;
        }

        public void setInstgramToken(String instgramToken) {
            this.instgramToken = instgramToken;
        }

        public String getSpotifyAccessToken() {
            return spotifyAccessToken;
        }

        public void setSpotifyAccessToken(String spotifyAccessToken) {
            this.spotifyAccessToken = spotifyAccessToken;
        }

        public String getSpotifyRefreshToken() {
            return spotifyRefreshToken;
        }

        public void setSpotifyRefreshToken(String spotifyRefreshToken) {
            this.spotifyRefreshToken = spotifyRefreshToken;
        }

        public String getIsShowWorkOn() {
            return isShowWorkOn;
        }

        public void setIsShowWorkOn(String isShowWorkOn) {
            this.isShowWorkOn = isShowWorkOn;
        }

        public String getIsShowSchoolOn() {
            return isShowSchoolOn;
        }

        public void setIsShowSchoolOn(String isShowSchoolOn) {
            this.isShowSchoolOn = isShowSchoolOn;
        }

        public String getIsShowGenderOn() {
            return isShowGenderOn;
        }

        public void setIsShowGenderOn(String isShowGenderOn) {
            this.isShowGenderOn = isShowGenderOn;
        }

        public String getShowMe() {
            return showMe;
        }

        public void setShowMe(String showMe) {
            this.showMe = showMe;
        }

        public String getShieldBadInfo() {
            return shieldBadInfo;
        }

        public void setShieldBadInfo(String shieldBadInfo) {
            this.shieldBadInfo = shieldBadInfo;
        }

        public String getSpotifyId() {
            return spotifyId;
        }

        public void setSpotifyId(String spotifyId) {
            this.spotifyId = spotifyId;
        }

        public String getImPassword() {
            return imPassword;
        }

        public void setImPassword(String imPassword) {
            this.imPassword = imPassword;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }

        public int getIsVip() {
            return isVip;
        }

        public void setIsVip(int isVip) {
            this.isVip = isVip;
        }

        public int getIsAddSpeed() {
            return isAddSpeed;
        }

        public void setIsAddSpeed(int isAddSpeed) {
            this.isAddSpeed = isAddSpeed;
        }

        public String getIs_display() {
            return is_display;
        }

        public void setIs_display(String is_display) {
            this.is_display = is_display;
        }
    }
}
