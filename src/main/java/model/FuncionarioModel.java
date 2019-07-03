package model;

import java.util.List;

import model.Entidades.Funcionario;
import model.Exception.CpfException;
import model.Exception.EmailException;
import model.Exception.JaExisteException;
import model.Exception.NullException;
import model.Exception.StringException;
import model.Exception.TelefoneException;
import model.Exception.ValorException;
import model.dao.FuncionarioDaoImpl;
import model.util.Validacoes;

public class FuncionarioModel {
	private FuncionarioDaoImpl dao = new FuncionarioDaoImpl();

	public void registraFuncionario(Funcionario f) throws EmailException, TelefoneException, CpfException,
			StringException, NullException, JaExisteException, ValorException {
		if (f != null) {
			if (!this.existe(f)) {
				if (Validacoes.verificaString(f.getNome())
						&& Validacoes.verificaString(f.getFuncao())) {
					if (Validacoes.verificaCpf(f.getCpf())) {
						if (Validacoes.verificaTelefone(f.getTelefone())) {
							if (Validacoes.verificaEmail(f.getEmail())) {
								if (Validacoes.verificaValor(f.getSalario())) {
									dao.insert(f);
								} else {
									throw new ValorException("Salário inválido");
								}
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
					throw new StringException("Nome, Usuario ou Função inválidos");
				}
			} else {
				throw new JaExisteException("Este funcionário já existe");
			}
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	public void removeFuncionario(Funcionario f) {
		dao.remove(f);
	}

	public void atualizaFuncionario(Funcionario f)
			throws StringException, ValorException, EmailException, TelefoneException, CpfException, NullException {
		if (!this.existe(f)) {
			if (Validacoes.verificaString(f.getNome())
					&& Validacoes.verificaString(f.getFuncao())) {
				if (Validacoes.verificaCpf(f.getCpf())) {
					if (Validacoes.verificaTelefone(f.getTelefone())) {
						if (Validacoes.verificaEmail(f.getEmail())) {
							if (Validacoes.verificaValor(f.getSalario())) {
								dao.update(f);
							} else {
								throw new ValorException("Salário inválido");
							}
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
				throw new StringException("Nome, Usuario ou Função inválidos");
			}
		} else {
			throw new NullException("Nenhum item pode estar vazio");
		}
	}

	private boolean existe(Funcionario f) {
		boolean existe = false;
		try{
			dao.consultarPorCpf(f.getCpf());
			existe = true;
		} catch (Exception e) {
			
		}
		return existe;
	}
	
	public List<Funcionario> listarTodos() {
		return dao.listarTodos();
	}
}