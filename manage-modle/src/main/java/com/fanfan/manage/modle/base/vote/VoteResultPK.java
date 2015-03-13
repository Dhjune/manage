package com.fanfan.manage.modle.base.vote;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class VoteResultPK implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2901943761919120491L;

	@OneToOne
	@JoinColumn(name="VOTE_ID")
	private Vote vote;
	
	@OneToOne
	@JoinColumn(name="PLAN_ID")
	private VotePlan votePlan;
	
	@OneToOne
	@JoinColumn(name="OPTION_ID")
	private VoteOption  voteOption;
	
}
