package model;

import java.util.List;

import model.Entidades.Cliente;
import model.Exception.CpfException;
import model.Exception.EmailException;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.StringException;
import model.Exception.TelefoneException;
import model.dao.ClienteDaoImpl;
import model.util.Validacoes;

public class ClienteModel {

	private ClienteDaoImpl dao = new ClienteDaoImpl();

	public void registraCliente(Cliente c)
			throws JaExisteException, NullException, StringException, CpfException, TelefoneException, EmailException {
		if (c != null) {
			if (!this.existe(c)) {
				if (Validacoes.verificaString(c.getNome())) {
					if (Validacoes.verificaCpf(c.getCpf())) {
						if (Validacoes.verificaTelefone(c.getTelefone())) {
							if (Validacoes.verificaEmail(c.getEmail())) {
								dao.insert(c);
							} else {
								throw new EmailException("Email inválido");
							}
						} else {
							throw new TelefoneException("Telefone inválido");
						}
					} else {
						throw new CpfException("Cpf inválido");
					}
				} else {
					throw new StringException("Nome ou Usuario inválidos");
				}
			} else {
				throw new JaExisteException("Este Cliente já existe");
			}
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void atualizaCliente(Cliente c) throws EmailException, TelefoneException, CpfException, StringException, NullException {
		if (!this.existe(c)) {
			if (Validacoes.verificaString(c.getNome())) {
				if (Validacoes.verificaCpf(c.getCpf())) {
					if (Validacoes.verificaTelefone(c.getTelefone())) {
						if (Validacoes.verificaEmail(c.getEmail())) {
							dao.update(c);
						} else {
							throw new EmailException("Email inválido");
						}
					} else {
						throw new TelefoneException("Telefone inválido");
					}
				} else {
					throw new CpfException("Cpf inválido");
				}
			} else {
				throw new StringException("Nome ou Usuario inválidos");
			}
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void removeCliente(Cliente c) {
		dao.remove(c);
	}

	private boolean existe(Cliente c) {
		boolean existe = false;
		try{
			dao.consultarPorCpf(c.getCpf());
			existe = true;
		} catch (Exception e) {
			
		}
		return existe;
	}

	
	public List<Cliente> ListarTodos() {
		return dao.ListarTodos();
	}	
}