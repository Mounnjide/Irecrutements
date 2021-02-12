package com.irecrutements.spring.business;

import java.util.List;

import com.irecrutements.spring.models.Document;

public interface DocumentManager {
	public int add(Document c);
	public int update(Document c);
	public int delete(Document c);
	public List<Document> selectAll();
	public Document selectById(int id);
	public List<Document> searchByTitreDocument(String titreDocument);
	public List<Document> searchByTypeDocument(String typeDocument);
	
}
