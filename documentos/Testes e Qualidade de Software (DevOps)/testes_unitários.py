# -*- coding: utf-8 -*-
"""Testes_Unitários.ipynb

Automatically generated by Colab.

Original file is located at
    https://colab.research.google.com/drive/1pCLAL3hNWM3mHzqEoUtIBZFGHmYCpHX3
"""

import unittest
from app import login

class TestLogin(unittest.TestCase):
    def test_login_valido(self):
        # Teste com login e senha válidos
        usuario = "usuario_teste"
        senha = "senha_teste"
        resultado = login(usuario, senha)
        self.assertEqual(resultado, "Login bem-sucedido")

    def test_login_invalido(self):
        # Teste com login ou senha inválidos
        usuario = "usuario_errado"
        senha = "senha_errada"
        resultado = login(usuario, senha)
        self.assertEqual(resultado, "Login ou senha incorretos")

if __name__ == '__main__':
    unittest.main()

# Testei as funções de login com dados válidos e inválidos.
# Isso é importante porque a tela de login precisa garantir
# que o usuário tenha acesso apenas se as credenciais estiverem corretas.

import unittest
from app import cadastro

class TestCadastro(unittest.TestCase):
    def test_cadastro_valido(self):
        # Teste com dados de cadastro válidos
        usuario = "novo_usuario"
        senha = "senha_segura"
        email = "email@teste.com"
        resultado = cadastro(usuario, senha, email)
        self.assertEqual(resultado, "Cadastro realizado com sucesso")

    def test_cadastro_invalido(self):
        # Teste com dados inválidos (campo vazio)
        usuario = ""
        senha = "senha_segura"
        email = "email@teste.com"
        resultado = cadastro(usuario, senha, email)
        self.assertEqual(resultado, "Usuário não pode estar vazio")

if __name__ == '__main__':
    unittest.main()

# Realizei testes para verificar se o cadastro está funcionando corretamente.
# Também coloquei um cenário onde o usuário tenta se cadastrar sem preencher
# o nome de usuário, o que não pode acontecer.

import unittest
from app import ir_para_perfil

class TestNavegacaoPerfil(unittest.TestCase):
    def test_navegacao_para_perfil(self):
        # Verifica se ao clicar no ícone de perfil, a navegação ocorre corretamente
        resultado = ir_para_perfil()
        self.assertEqual(resultado, "Página de perfil carregada com sucesso")

if __name__ == '__main__':
    unittest.main()

# Testei a navegação para a tela de perfil, que é um dos ícones no rodapé.
# Isso garante que ao clicar no ícone, a página do perfil será carregada corretamente.

import unittest
from app import ir_para_receitas

class TestNavegacaoReceitas(unittest.TestCase):
    def test_navegacao_para_receitas(self):
        # Verifica se a navegação para a página de receitas funciona
        resultado = ir_para_receitas()
        self.assertEqual(resultado, "Página de receitas carregada com sucesso")

if __name__ == '__main__':
    unittest.main()

# Aqui, fiz o teste para garantir que a navegação até a página de receitas está
# funcionando corretamente quando o usuário clica no ícone correspondente.