package pe.edu.upeu.enums;

public enum NivelEducativo {
    PREESCOLAR, PRIMARIA, SECUNDARIA, PREPARATORIA;

    @Override
    public String toString() {
        switch (this) {
            case PREESCOLAR: return "Preescolar";
            case PRIMARIA: return "Primaria";
            case SECUNDARIA: return "Secundaria";
            case PREPARATORIA: return "Preparatoria";
            default: return super.toString();
        }
    }
}