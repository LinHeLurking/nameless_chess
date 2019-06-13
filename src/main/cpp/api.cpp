#include "online_ruin_of_future_nameless_chess_CppSync.h"
#include "dep.h"

using namespace std;

enum { success = 1, failure = -1 };

Game game;

int move_encode(pair<int, int> move) {
	return move.first * 1000 + move.second;
}



JNIEXPORT jint JNICALL Java_online_ruin_1of_1future_nameless_1chess_CppSync_sync_1nothing(JNIEnv*, jobject) {
	return success;
}


JNIEXPORT jint JNICALL Java_online_ruin_1of_1future_nameless_1chess_CppSync_sync_1drawboard_1in_1commandline(JNIEnv *, jobject){
	game.board.drawboard();
	return success;
}


JNIEXPORT jint JNICALL Java_online_ruin_1of_1future_nameless_1chess_CppSync_sync_1gamemode(JNIEnv*, jobject, jint mode) {
	/*
		mode:
			1 -> four human players
			2 -> one human player and three computer players
			3 -> four computer players (just for fun)
	*/
	switch (mode) {
	case 1:
		for (int i = 0; i < game.player_cnt; ++i) {
			game.player_role[i] = Game::role::human;
		}
		game.game_mode = Game::mode::four_human;
		break;
	case 2:
		game.player_role[0] = Game::role::human;
		for (int i = 1; i < game.player_cnt; ++i) {
			game.player_role[i] = Game::role::computer;
		}
		game.game_mode = Game::mode::one_human;
		break;
	case 3:
		for (int i = 0; i < game.player_cnt; ++i) {
			game.player_role[i] = Game::role::computer;
		}
		game.game_mode = Game::mode::zero_human;
		break;
	default:
		return failure;
	}
	return success;
}


JNIEXPORT jint JNICALL Java_online_ruin_1of_1future_nameless_1chess_CppSync_sync_1java_1manual_1move(JNIEnv*, jobject, jint from_x, jint from_y, jint to_x, jint to_y){
	int status = game.move_piece(game.board.pos_encode(from_x, from_y), game.board.pos_encode(to_x, to_y));
	if (status != Game::move::error) {
		game.cur_player = (game.cur_player + 1) % game.player_cnt;
	}
	return status;
}

JNIEXPORT jint JNICALL Java_online_ruin_1of_1future_nameless_1chess_CppSync_sync_1cpp_1auto_1move_1_1raw(JNIEnv*, jobject)
{
	// at current stage, this fuction only supports random move

	pair<int, int> move = game.random_move();

	int status = game.move_piece(move);

	if (status != Game::move::error) {
		game.cur_player = (game.cur_player + 1) % game.player_cnt;
	}
	else {
		game.log("wrong auto move");
	}
	
	return move_encode(move);
}



JNIEXPORT jint JNICALL Java_online_ruin_1of_1future_nameless_1chess_CppSync_sync_1unforgiving_1game_1loop(JNIEnv*, jobject) {
	game.game_loop();
	return 0;
}

JNIEXPORT jint JNICALL Java_online_ruin_1of_1future_nameless_1chess_CppSync_sync_1forgive_1game_1loop(JNIEnv*, jobject) {
	game.set_exit_flag(true);
	return 0;
}

