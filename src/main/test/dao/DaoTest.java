package dao;

import java.util.List;

public interface DaoTest <O> {
	void insert(O obj);
	
	void update(O obj);
	
	void remove(O obj);
	
	O findbyID(Integer id);
	
	List<O> findALL();
}
