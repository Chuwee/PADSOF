package Pedido;
/**
 * @author Paloma Ballester Asesio, Ignacio Ildefonso del Miguel Ruano y María del Pinar Sacristán Matesanz
 * 
 */
/**
 * 
 *esta enumeracion muestra los diferentes estados en los que puede estar un pedido
 *
 */
public enum EstadoPedido {
	EnConstruccion,
    EnReparto,
    EnRepartoFallido1,
    Entregado,
    Construido,
    Validado,
    PendienteEmpaquetar,
    NoEntregado
}
