package cn.net.msg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;
/*
 * 实体类，对应着数据库中的表t_answer
 */
@Component("wenbaAnswer")
@Entity
@Table(name="t_answer")
public class WenbaAnswer{
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
	
	@Column(name="question_id")
	private long questionId;
	
	
	@Column(name="answer_digest")
	private String answerDigest;
	
	@Column(name="answer_content")
	private String answerContent;
	
	@Column(name="answer_conclusion")
	private String answerConclusion;

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

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getAnswerDigest() {
		return answerDigest;
	}

	public void setAnswerDigest(String answerDigest) {
		this.answerDigest = answerDigest;
	}

	public String getAnswerContent() {
		return answerContent;
	}

	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}

	public String getAnswerConclusion() {
		return answerConclusion;
	}

	public void setAnswerConclusion(String answerConclusion) {
		this.answerConclusion = answerConclusion;
	}
}
