package com.bmob.settablenamedemo;

import cn.bmob.v3.BmobObject;

public class GameScore extends BmobObject {
	private String playerName;
    private Integer score;
    private Boolean cheatMode;

    public GameScore() {
        this.setTableName("T_a_b");
    }

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Boolean getCheatMode() {
		return cheatMode;
	}

	public void setCheatMode(Boolean cheatMode) {
		this.cheatMode = cheatMode;
	}

    
}
