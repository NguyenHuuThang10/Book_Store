package model;

import java.sql.Date;
import java.util.Objects;

public class Author {
	private String id;
	private String name;
	private Date birthday;
	private String story;
	
	
	public Author() {
		super();
	}

	public Author(String id, String name, Date birthday, String story) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.story = story;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	@Override
	public int hashCode() {
		return Objects.hash(birthday, id, name, story);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Author other = (Author) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", birthday=" + birthday + ", story=" + story + "]";
	}
	
	
	
	
	
	
	
	
}
