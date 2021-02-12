package com.irecrutements.spring.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irecrutements.spring.dao.DocumentDao;
import com.irecrutements.spring.models.Document;
@Service
public class DocumentManagerDefault implements DocumentManager{
	@Autowired
	private DocumentDao dao;
//	public DocumentManagerDefault() {
//		// TODO Auto-generated constructor stub
//	}
//	public DocumentManagerDefault(DocumentDao dao) {
//		super();
//		this.dao = dao;
//	}
//	
//	public DocumentDao getDao() {
//		return dao;
//	}
//	public void setDao(DocumentDao dao) {
//		this.dao = dao;
//	}
	public int add(Document c) {
		return dao.insert(c);
	}

	public int update(Document c) {
		return dao.update(c);
	}

	public int delete(Document c) {
		return dao.delete(c);
	}

	public List<Document> selectAll() {
		return dao.selectAll();
	}

	public Document selectById(int id) {
		return dao.selectById(id);
	}

	public List<Document> searchByTitreDocument(String titreDocument) {
		return dao.selectByTitreDocument(titreDocument);
	}

	public List<Document> searchByTypeDocument(String typeDocument) {
		return dao.selectByTypeDocument(typeDocument);
	}

}
