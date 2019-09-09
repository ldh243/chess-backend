package com.chess.chessapi.constants;

import java.io.Serializable;

public class EloRatingLevel implements Serializable {
    public static final int BEGINNER_ID = 1;
    public static final int MINOR_ID = 2;
    public static final int INTERMEDIATE_ID = 3;
    public static final int MAJOR_ID = 4;
    public static final int MASTER_ID = 5;

    public static final int BEGINNER_ELO = 800;
    public static final int MINOR_ELO = 1000;
    public static final int INTERMEDIATE_ELO = 1200;
    public static final int MAJOR_ELO = 1400;
    public static final int MASTER_ELO = 1600;

    public static int getEloById(int id){
        int elo = 0;

        switch (id){
            case BEGINNER_ID:
                elo = BEGINNER_ELO;
                break;
            case MINOR_ID:
                elo = MINOR_ELO;
                break;
            case INTERMEDIATE_ID:
                elo = INTERMEDIATE_ELO;
                break;
            case MAJOR_ID:
                elo = MAJOR_ELO;
                break;
            case MASTER_ID:
                elo = MASTER_ELO;
                break;
        }

        return elo;
    }
}