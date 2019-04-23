package com.sy.pojo;

/**
 * 用户信息 
 * @author yy
 * */
public class User {
	private String userId;			//用户Id
	private String account;			//微信openid
	private String name;			//用户昵称
	private Integer sex;			//用户性别
	private String headimg;			//用户头像
	private Double coins;			//用户金币
	private Integer gems;			//用户房卡
	private String ip;				//用户登录的IP
   
	private String clientId;		//个推 CID
	
	private Integer invitation;		//邀请码
	private Integer invitationOther; //
	private String phoneNumber;		//手机号
	private String identifyCard;	//身份证号码
	
	private String gps;				//用户gps
	private Integer isManager;		//茶楼中的身份 传递消息用
	
	private int gamesNum;			//用户游戏次数
	private int winNum;				//用户获胜次数
	
	public void updata(User user){
		this.name = user.name;
		this.sex = user.sex;
		this.headimg = user.headimg;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId.toString();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public Double getCoins() {
		return coins;
	}

	public void setCoins(Double coins) {
		this.coins = coins;
	}

	public Integer getGems() {
		return gems;
	}

	public void setGems(Integer gems) {
		this.gems = gems;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Integer getIsManager() {
		return isManager;
	}

	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}
	
	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public Integer getInvitation() {
		return invitation;
	}

	public void setInvitation(Integer invitation) {
		this.invitation = invitation;
	}

	public Integer getInvitationOther() {
		return invitationOther;
	}

	public void setInvitationOther(Integer invitationOther) {
		this.invitationOther = invitationOther;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIdentifyCard() {
		return identifyCard;
	}

	public void setIdentifyCard(String identifyCard) {
		this.identifyCard = identifyCard;
	}

	public int getGamesNum() {
		return gamesNum;
	}

	public void setGamesNum(int gamesNum) {
		this.gamesNum = gamesNum;
	}

	public int getWinNum() {
		return winNum;
	}

	public void setWinNum(int winNum) {
		this.winNum = winNum;
	}

	public void setWechatInfo(User wechatUser){
		this.account = wechatUser.getAccount();
		this.name = wechatUser.getName();
		this.sex = wechatUser.getSex();
		this.headimg = wechatUser.getHeadimg();
	}
	
}
