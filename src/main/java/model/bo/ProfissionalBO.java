package model.bo;

import java.util.ArrayList;

import model.dao.ProfissionalDAO;
import model.vo.Profissional;

public class ProfissionalBO {

	ProfissionalDAO dao = new ProfissionalDAO();
	public Profissional salvar(Profissional profissional) {
		
		return dao.salvar(profissional);
	}
	public ArrayList<Profissional> consultarTodos() {
		// TODO Auto-generated method stub
		return dao.consultarTodos();
	}

}
