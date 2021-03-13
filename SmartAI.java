import java.util.ArrayList;
import java.util.TreeMap;

public class SmartAI implements IOthelloAI {

	// DumAI:
    // public Position decideMove(GameState s){
	// 	ArrayList<Position> moves = s.legalMoves();
	// 	if ( !moves.isEmpty() )
	// 		return moves.get(0);
	// 	else
	// 		return new Position(-1,-1);
	// }    

    public Position decideMove(GameState s) { // Minimax-Decision method
		ArrayList<Position> moves = s.legalMoves();
		TreeMap<Integer,Position> actions = new TreeMap<Integer,Position>();
		for (Position move : moves){
			s.insertToken(move); 
			actions.put(minValue(s), move);
		}
		return actions.get(actions.lastKey());
    }
	public int maxValue(GameState s){
		if (s.isFinished()){
			return (s.countTokens()[1] - s.countTokens()[2]);
		} else {
			int v = Integer.MIN_VALUE;
			ArrayList<Position> moves = s.legalMoves();
			for (Position move : moves){
				s.insertToken(move);
				v = Integer.max(v, minValue(s));
			}
			return v;
		}
	}
	public int minValue(GameState s){
		if (s.isFinished()){
			return (s.countTokens()[1] - s.countTokens()[2]);
		} else {
			int v = Integer.MAX_VALUE;
			ArrayList<Position> moves = s.legalMoves();
			for (Position move : moves){
				s.insertToken(move);
				v = Integer.max(v, maxValue(s));
			}
			return 	v;
		}
	}
}
