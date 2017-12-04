package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the BHUSER database table.
 * 
 */
@Entity
@NamedQuery(name="Bhuser.findAll", query="SELECT b FROM Bhuser b")
public class Bhuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long bhuserid;

	@Temporal(TemporalType.DATE)
	private Date joindate;

	private String motto;

	private String useremail;

	private String username;

	private String userpassword;

	//bi-directional many-to-one association to Bhpost
	@OneToMany(mappedBy="bhuser")
	private List<Bhpost> bhposts;

	public Bhuser() {
	}

	public long getBhuserid() {
		return this.bhuserid;
	}

	public void setBhuserid(long bhuserid) {
		this.bhuserid = bhuserid;
	}

	public Date getJoindate() {
		return this.joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}

	public String getMotto() {
		return this.motto;
	}

	public void setMotto(String motto) {
		this.motto = motto;
	}

	public String getUseremail() {
		return this.useremail;
	}

	public void setUseremail(String useremail) {
		this.useremail = useremail;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return this.userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public List<Bhpost> getBhposts() {
		return this.bhposts;
	}

	public void setBhposts(List<Bhpost> bhposts) {
		this.bhposts = bhposts;
	}

	public Bhpost addBhpost(Bhpost bhpost) {
		getBhposts().add(bhpost);
		bhpost.setBhuser(this);

		return bhpost;
	}

	public Bhpost removeBhpost(Bhpost bhpost) {
		getBhposts().remove(bhpost);
		bhpost.setBhuser(null);

		return bhpost;
	}

}