package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.CriarUsuarioDto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class CriarUsuarioController {

	@Autowired // inicialização automática!
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/criar-usuario")
	public ModelAndView criarUsuario() {

		// WEB-INF/views/criar-usuario.jsp
		ModelAndView modelAndView = new ModelAndView("criar-usuario");
		modelAndView.addObject("dto", new CriarUsuarioDto());
		return modelAndView;
	}

	@RequestMapping(value = "/criar-usuario-post", method = RequestMethod.POST)
	public ModelAndView criarUsuarioPost(CriarUsuarioDto dto) {

		// TODO

		ModelAndView modelAndView = new ModelAndView("criar-usuario");
		try {

			Usuario usuario = new Usuario();

			usuario.setNome(dto.getNome());
			usuario.setEmail(dto.getEmail());
			usuario.setSenha(dto.getSenha());

			usuarioRepository.create(usuario);
			dto = new CriarUsuarioDto();

			modelAndView.addObject("mensagem", "Parabéns! Usuário cadastrado com sucesso.");
		} catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}

		modelAndView.addObject("dto", dto);
		return modelAndView;
	}

}
