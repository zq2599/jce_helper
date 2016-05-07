package cn.net.msg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
/*
 * 实体类，对应着数据库中的表t_user
 */
@Component("wenbaQuestion")
@Entity
@Table(name="t_question")
public class WenbaQuestion{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	@Column(name="creator_id")
	private long creatorId;
	
	@Column(name="create_time")
	private long createTime;
	
	@Column(name="modify_time")
	private long modifyTime;
	
	@Column(name="question_title")
	private String questionTitle;
	
	@Column(name="question_content")
	private String questionContent;
	
	@Column(name="question_type")
	private String questionType;
	
	@Column(name="question_status")
	private String questionStatus;
	
	@Column(name="question_finish_time")
	private long questionFinishTime;
	
	@Column(name="question_price")
	private long questionPrice;
	
	@Column(name="answers")
	private String answers;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getQuestionTitle() {
		return questionTitle;
	}

	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionStatus() {
		return questionStatus;
	}

	public void setQuestionStatus(String questionStatus) {
		this.questionStatus = questionStatus;
	}

	public long getQuestionFinishTime() {
		return questionFinishTime;
	}

	public void setQuestionFinishTime(long questionFinishTime) {
		this.questionFinishTime = questionFinishTime;
	}

	public long getQuestionPrice() {
		return questionPrice;
	}

	public void setQuestionPrice(long questionPrice) {
		this.questionPrice = questionPrice;
	}

	public String getAnswers() {
		return answers;
	}

	public void setAnswers(String answers) {
		this.answers = answers;
	}
	
	
}
