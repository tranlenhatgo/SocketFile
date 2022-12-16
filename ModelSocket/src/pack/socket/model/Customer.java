package pack.socket.model;

import java.io.Serializable;

public class Customer implements Serializable{

	private static final long serialVersionUID = 1L;
	private String Id;
	private String Name;
	private String Desc;
	public Customer(String id, String name, String desc) {
		Id = id;
		Name = name;
		Desc = desc;
	}
	public String getId() {
		return Id;
	}
	public String getName() {
		return Name;
	}
	public String getDesc() {
		return Desc;
	}
	
	
}
