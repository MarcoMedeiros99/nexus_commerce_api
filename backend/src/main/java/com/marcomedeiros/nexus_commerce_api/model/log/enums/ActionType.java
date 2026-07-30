package com.marcomedeiros.nexus_commerce_api.model.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ActionType {
    CREATE_PRODUTO(1, "Produto Criado"),
    DELETE_PEDIDO(2, "Pedido Removido"),
    UPDATE_PRODUTO(3, "Produto Atualizado"),
    PRODUTO_PRECO_ALTERADO(4, "Preço do Produto Alterado"),
    ESTOQUE_AJUSTE_MANUAL(5, "Ajuste Manual de Estoque"),
    LOGIN_SUCESSO(6, "Login com Sucesso"),
    LOGIN_FALHA(7, "Falha no Login"),
    USUARIO_BLOQUEADO(8, "Usuário Bloqueado"),
    PEDIDO_CRIADO(9, "Pedido Criado"),
    PEDIDO_STATUS_ALTERADO(10, "Status do Pedido Alterado"),
    CUPOM_APLICADO(11, "Cupom Aplicado"),
    PAGAMENTO_ESTORNADO(12, "Pagamento Estornado"),
    CARGO_ALTERADO(13, "Cargo Alterado"),
    LOGS_LIMPEZA(14, "Limpeza de Logs"),
    PASSWORD_CHANGED(15, "Senha Alterada"),
    UNAUTHORIZED_ACCESS(16, "Tentativa de Acesso Não Autorizado"),
    COUPON_CREATED(17, "Cupom Criado"),
    LOGOUT(18, "Logout Realizado"),
    ORDER_REFUNDED(19, "Pedido Reembolsado");

    private final Integer code;
    private final String description;

    public static ActionType toEnum(Integer code) {
        if (code == null) return null;
        for (ActionType value : ActionType.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Código de ação inválido: " + code);
    }
}
