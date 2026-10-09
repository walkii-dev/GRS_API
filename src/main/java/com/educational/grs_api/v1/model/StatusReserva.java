package com.educational.grs_api.v1.model;

public enum StatusReserva {
    ATIVA{
        @Override
        public boolean transicionaPara(StatusReserva novoStatus){
            return novoStatus == CANCELADA;
        }
    },
    CANCELADA{
        @Override
        public boolean transicionaPara(StatusReserva novoStatus){
            return false;
        }
    };
    public abstract boolean transicionaPara(StatusReserva novoStatus);
}
