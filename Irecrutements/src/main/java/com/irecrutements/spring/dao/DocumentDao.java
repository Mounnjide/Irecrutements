package com.irecrutements.spring.dao;

import java.util.List;

import com.irecrutements.spring.models.Document;

public interface DocumentDao {
	public int insert(Document c);
	public int update(Document c);
	public int delete(Document c);
	public List<Document> selectAll();
	public Document selectById(int id);
	public List<Document> selectByTitreDocument(String titreDocument);
	public List<Document> selectByTypeDocument(String typeDocument);
	
}
