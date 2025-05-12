/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author luiza
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * Classe principal que representa a interface gráfica do sistema bancário
 */
public class View extends javax.swing.JFrame {
    // Mapa para armazenar todas as contas (número da conta -> objeto Conta)
    private HashMap<Integer, Conta> contas = new HashMap<>();
    
    // Mapa para armazenar as senhas das contas (número da conta -> senha)
    private HashMap<Integer, String> senhas = new HashMap<>();
    
    // Referência para a conta atualmente logada
    private Conta contaAtual;
    
    // Componentes da interface
    private JLabel lblStatus;
    private JButton btnSair;

    /**
     * Construtor da classe View
     */
    public View() {
        initComponents(); // Inicializa componentes gerados pelo NetBeans
        initCustomComponents(); // Inicializa componentes customizados
        atualizarStatus(); // Atualiza o status inicial
        setTitle("Sistema Bancário");
        setSize(500, 400);
        setLocationRelativeTo(null); // Centraliza a janela
    }

    /**
     * Inicializa componentes customizados não gerados pelo NetBeans
     */
    private void initCustomComponents() {
        // Label para mostrar o status da conta
        lblStatus = new JLabel("Nenhuma conta ativa", SwingConstants.CENTER);
        lblStatus.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Botão para sair da conta
        btnSair = new JButton("Sair da Conta");
        btnSair.setEnabled(false); // Inicialmente desabilitado
        btnSair.addActionListener(e -> sairDaConta());
        
        // Adiciona os componentes ao painel principal
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(lblStatus, BorderLayout.NORTH);
        contentPane.add(btnSair, BorderLayout.SOUTH);
        contentPane.revalidate();
    }

    /**
     * Método para deslogar da conta atual
     */
    private void sairDaConta() {
        contaAtual = null;
        atualizarStatus();
    }

    /**
     * Atualiza o status da interface com base na conta ativa
     */
    private void atualizarStatus() {
        if (contaAtual == null) {
            // Nenhuma conta ativa
            lblStatus.setText("Nenhuma conta ativa");
            btnEntrar.setEnabled(!contas.isEmpty()); // Só habilita entrar se houver contas
            btnFecharConta.setEnabled(false);
            btnSair.setEnabled(false);
        } else {
            // Conta ativa - mostra informações
            lblStatus.setText(String.format(
                "Conta %d - %s (Saldo: R$ %.2f)",
                contaAtual.getNumero(),
                contaAtual.getCliente().getNome(),
                contaAtual.getSaldo()
            ));
            btnEntrar.setEnabled(false);
            btnFecharConta.setEnabled(true);
            btnSair.setEnabled(true);
        }
    }

    /**
     * Mostra o menu de operações bancárias
     */
    private void mostrarMenuOperacoes() {
        // Verifica se há uma conta ativa
        if (contaAtual == null) {
            JOptionPane.showMessageDialog(this,
                "Erro: Nenhuma conta selecionada!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cria a janela de diálogo para operações
        JDialog operacoesDialog = new JDialog(this, "Operações Bancárias", true);
        operacoesDialog.setSize(500, 400);
        operacoesDialog.setLayout(new BorderLayout(10, 10));
        operacoesDialog.getContentPane().setBackground(new Color(240, 240, 240));

        // Área de texto com informações da conta
        JTextArea txtInfo = new JTextArea(contaAtual.toString());
        txtInfo.setEditable(false);
        operacoesDialog.add(new JScrollPane(txtInfo), BorderLayout.NORTH);

        // Painel com botões de operações
        JPanel operPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        
        // Botões das operações
        JButton btnDepositar = new JButton("Depositar");
        JButton btnSacar = new JButton("Sacar");
        JButton btnMensalidade = new JButton("Pagar Mensalidade");
        JButton btnExtrato = new JButton("Ver Extrato");
        JButton btnSairConta = new JButton("Sair da Conta");

        // Ação para depósito
        btnDepositar.addActionListener(e -> {
            String valorStr = JOptionPane.showInputDialog(operacoesDialog, "Valor para depósito:", "0.00");
            try {
                double valor = Double.parseDouble(valorStr);
                if (contaAtual.depositar(valor, "Depósito")) {
                    txtInfo.setText(contaAtual.toString()); // Atualiza informações
                    atualizarStatus(); // Atualiza status
                    JOptionPane.showMessageDialog(operacoesDialog, "Depósito realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(operacoesDialog, "Falha no depósito!", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(operacoesDialog, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação para saque
        btnSacar.addActionListener(e -> {
            String valorStr = JOptionPane.showInputDialog(operacoesDialog, "Valor para saque:", "0.00");
            try {
                double valor = Double.parseDouble(valorStr);
                if (contaAtual.sacar(valor, "Saque")) {
                    txtInfo.setText(contaAtual.toString());
                    atualizarStatus();
                    JOptionPane.showMessageDialog(operacoesDialog, "Saque realizado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(operacoesDialog, "Falha no saque! Verifique seu saldo.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(operacoesDialog, "Valor inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação para pagar mensalidade
        btnMensalidade.addActionListener(e -> {
            if (contaAtual.cobrarMensalidade()) {
                txtInfo.setText(contaAtual.toString());
                atualizarStatus();
                JOptionPane.showMessageDialog(operacoesDialog, "Mensalidade paga com sucesso!");
            } else {
                JOptionPane.showMessageDialog(operacoesDialog, "Falha ao pagar mensalidade!", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação para ver extrato
        btnExtrato.addActionListener(e -> {
            StringBuilder extrato = new StringBuilder("Extrato Bancário\n");
            contaAtual.getExtrato().forEach(op -> extrato.append(op).append("\n"));
            JOptionPane.showMessageDialog(operacoesDialog, extrato.toString(), "Extrato", JOptionPane.INFORMATION_MESSAGE);
        });

        // Ação para sair da conta
        btnSairConta.addActionListener(e -> {
            operacoesDialog.dispose();
            sairDaConta();
        });

        // Adiciona os botões ao painel
        operPanel.add(btnDepositar);
        operPanel.add(btnSacar);
        operPanel.add(btnMensalidade);
        operPanel.add(btnExtrato);
        operPanel.add(btnSairConta);
        
        // Adiciona o painel à janela
        operacoesDialog.add(operPanel, BorderLayout.CENTER);
        operacoesDialog.setLocationRelativeTo(this);
        operacoesDialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAbrirConta = new javax.swing.JButton();
        btnFecharConta = new javax.swing.JButton();
        lblIntro = new javax.swing.JLabel();
        btnEntrar = new javax.swing.JButton();
        lblimagemEntrar = new javax.swing.JLabel();
        lblimagemAbrirConta = new javax.swing.JLabel();
        lblimagemFecharConta = new javax.swing.JLabel();
        lblimagemBanco = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAbrirConta.setText("Abrir Conta");
        btnAbrirConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirContaActionPerformed(evt);
            }
        });

        btnFecharConta.setText("Fechar Conta");
        btnFecharConta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecharContaActionPerformed(evt);
            }
        });

        lblIntro.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIntro.setText("Bem-vindo ao menu de operações do Banco! Escolha uma das opções a seguir para continuar:");

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });

        lblimagemEntrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/entrar.png"))); // NOI18N

        lblimagemAbrirConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adicionar-usuario.png"))); // NOI18N

        lblimagemFecharConta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/negocios.png"))); // NOI18N

        lblimagemBanco.setIcon(new javax.swing.ImageIcon("C:\\Users\\luiza\\OneDrive\\Imagens\\banco.png")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblimagemEntrar)
                    .addComponent(lblimagemAbrirConta)
                    .addComponent(lblimagemFecharConta)
                    .addComponent(lblimagemBanco))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIntro)
                    .addComponent(btnAbrirConta)
                    .addComponent(btnEntrar)
                    .addComponent(btnFecharConta))
                .addContainerGap(509, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIntro, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblimagemBanco))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrar)
                    .addComponent(lblimagemEntrar))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrirConta)
                    .addComponent(lblimagemAbrirConta))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFecharConta)
                    .addComponent(lblimagemFecharConta))
                .addContainerGap(142, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void btnAbrirContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirContaActionPerformed
        // Cria a janela de diálogo para abertura de conta
        JDialog dialog = new JDialog(this, "Abertura de Conta", true);
        dialog.setSize(450, 400);
        dialog.setLayout(new GridLayout(9, 2, 10, 10));
        
        // Componentes do formulário
        JTextField txtNome = new JTextField();
        JTextField txtCpf = new JTextField();
        JTextField txtEmail = new JTextField();
        JTextField txtTelefone = new JTextField();
        JComboBox<String> cbTipo = new JComboBox<>(new String[]{"Conta Corrente", "Conta Poupança"});
        JPasswordField txtSenha = new JPasswordField();
        JPasswordField txtConfirmaSenha = new JPasswordField();
        JTextField txtDataNascimento = new JTextField();
        JButton btnConfirmar = new JButton("Confirmar");

        // Configuração do campo de data de nascimento
        txtDataNascimento.setText("dd/MM/yyyy");
        txtDataNascimento.setForeground(Color.GRAY);
        txtDataNascimento.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (txtDataNascimento.getText().equals("dd/MM/yyyy")) {
                    txtDataNascimento.setText("");
                    txtDataNascimento.setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (txtDataNascimento.getText().isEmpty()) {
                    txtDataNascimento.setForeground(Color.GRAY);
                    txtDataNascimento.setText("dd/MM/yyyy");
                }
            }
        });

        // Adiciona os componentes ao diálogo
        dialog.add(new JLabel("Nome:"));
        dialog.add(txtNome);
        dialog.add(new JLabel("CPF:"));
        dialog.add(txtCpf);
        dialog.add(new JLabel("E-mail:"));
        dialog.add(txtEmail);
        dialog.add(new JLabel("Telefone:"));
        dialog.add(txtTelefone);
        dialog.add(new JLabel("Data Nasc. (dd/MM/yyyy):"));
        dialog.add(txtDataNascimento);
        dialog.add(new JLabel("Tipo de Conta:"));
        dialog.add(cbTipo);
        dialog.add(new JLabel("Senha:"));
        dialog.add(txtSenha);
        dialog.add(new JLabel("Confirmar Senha:"));
        dialog.add(txtConfirmaSenha);
        dialog.add(new JLabel());
        dialog.add(btnConfirmar);

        // Ação do botão confirmar
        btnConfirmar.addActionListener(e -> {
            try {
                // Validação das senhas
                String senha = new String(txtSenha.getPassword());
                String confirmaSenha = new String(txtConfirmaSenha.getPassword());
                
                if (!senha.equals(confirmaSenha)) {
                    throw new Exception("As senhas não coincidem!");
                }
                
                if (senha.length() < 4) {
                    throw new Exception("A senha deve ter pelo menos 4 caracteres!");
                }

                // Verificação de CPF duplicado
                String cpfDigitado = txtCpf.getText().replaceAll("[^0-9]", "");
                for (Conta conta : contas.values()) {
                    if (conta.getCliente().getCpf().equals(cpfDigitado)) {
                        throw new Exception("Já existe uma conta com este CPF!");
                    }
                }

                // Parse da data de nascimento
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                sdf.setLenient(false);
                Date dataNascimento;
                try {
                    dataNascimento = sdf.parse(txtDataNascimento.getText());
                } catch (Exception ex) {
                    throw new Exception("Data de nascimento inválida! Use o formato dd/MM/yyyy");
                }

                // Cria o cliente
                Cliente cliente = new Cliente(
                    txtNome.getText(),
                    txtCpf.getText(),
                    "",
                    txtTelefone.getText(),
                    txtEmail.getText(),
                    dataNascimento
                );

                // Cria a conta conforme o tipo selecionado
                String tipo = cbTipo.getSelectedIndex() == 0 ? "cc" : "cp";
                Conta novaConta = Conta.abrirConta(cliente, tipo);
                
                // Armazena a conta e a senha
                contas.put(novaConta.getNumero(), novaConta);
                senhas.put(novaConta.getNumero(), senha);
                
                // Mensagem de sucesso
                JOptionPane.showMessageDialog(dialog, 
                    "Conta criada com sucesso!\nNúmero: " + novaConta.getNumero(),
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                atualizarStatus();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog,
                    "Erro: " + ex.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnAbrirContaActionPerformed

    private void btnFecharContaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharContaActionPerformed
        // Verifica se há uma conta selecionada
        if (contaAtual == null) {
            JOptionPane.showMessageDialog(this,
                "Nenhuma conta selecionada!",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verifica se há saldo na conta
        if (contaAtual.getSaldo() > 0) {
            int opcao = JOptionPane.showConfirmDialog(this,
                "Você ainda tem saldo de R$ " + contaAtual.getSaldo() + ".\n" +
                "Deseja sacar o valor antes de fechar a conta?",
                "Saldo na conta", JOptionPane.YES_NO_OPTION);
            
            if (opcao == JOptionPane.YES_OPTION) {
                // Mostra diálogo para sacar o valor
                String valorStr = JOptionPane.showInputDialog(this,
                    "Digite o valor para sacar (máximo: R$ " + contaAtual.getSaldo() + "):",
                    contaAtual.getSaldo());
                
                try {
                    double valor = Double.parseDouble(valorStr);
                    if (contaAtual.sacar(valor, "Saque antes de fechar conta")) {
                        JOptionPane.showMessageDialog(this,
                            "Saque realizado com sucesso!\nSaldo restante: R$ " + contaAtual.getSaldo(),
                            "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this,
                            "Não foi possível realizar o saque!",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                        "Valor inválido!",
                        "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else {
                return; // Usuário cancelou
            }
        }

        // Tenta fechar a conta
        if (contaAtual.fecharConta()) {
            JOptionPane.showMessageDialog(this,
                "Conta fechada com sucesso!",
                "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            // Remove a conta dos mapas
            contas.remove(contaAtual.getNumero());
            senhas.remove(contaAtual.getNumero());
            contaAtual = null;
            atualizarStatus();
        } else {
            JOptionPane.showMessageDialog(this,
                "Não foi possível fechar a conta! Verifique se há saldo pendente.",
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFecharContaActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        // Verifica se há contas cadastradas
        if (contas.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Nenhuma conta cadastrada no sistema!\nPor favor, abra uma conta primeiro.",
                "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Cria a janela de login
        JDialog loginDialog = new JDialog(this, "Acessar Conta", true);
        loginDialog.setSize(350, 200);
        loginDialog.setLayout(new GridLayout(3, 2, 10, 10));

        // Componentes do login
        JTextField txtNumeroConta = new JTextField();
        JPasswordField txtSenha = new JPasswordField();
        JButton btnLogin = new JButton("Entrar");

        // Adiciona os componentes ao diálogo
        loginDialog.add(new JLabel("Número da Conta:"));
        loginDialog.add(txtNumeroConta);
        loginDialog.add(new JLabel("Senha:"));
        loginDialog.add(txtSenha);
        loginDialog.add(new JLabel());
        loginDialog.add(btnLogin);

        // Ação do botão de login
        btnLogin.addActionListener(e -> {
            try {
                int numero = Integer.parseInt(txtNumeroConta.getText());
                String senha = new String(txtSenha.getPassword());
                
                // Verifica se a conta existe
                if (!contas.containsKey(numero)) {
                    throw new Exception("Conta não encontrada!");
                }
                
                // Verifica a senha
                if (!senhas.get(numero).equals(senha)) {
                    throw new Exception("Senha incorreta!");
                }
                
                // Define a conta atual e atualiza a interface
                contaAtual = contas.get(numero);
                atualizarStatus();
                loginDialog.dispose();
                mostrarMenuOperacoes();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(loginDialog, "Número de conta inválido!", "Erro", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(loginDialog, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        loginDialog.setLocationRelativeTo(this);
        loginDialog.setVisible(true);
    }//GEN-LAST:event_btnEntrarActionPerformed
    
    public static void main(String args[]) {
        try {
            // Configura o look and feel (aparência) Nimbus
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(View.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Executa a interface na thread de eventos
        java.awt.EventQueue.invokeLater(() -> {
            new View().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrirConta;
    private javax.swing.JButton btnEntrar;
    private javax.swing.JButton btnFecharConta;
    private javax.swing.JLabel lblIntro;
    private javax.swing.JLabel lblimagemAbrirConta;
    private javax.swing.JLabel lblimagemBanco;
    private javax.swing.JLabel lblimagemEntrar;
    private javax.swing.JLabel lblimagemFecharConta;
    // End of variables declaration//GEN-END:variables
}
