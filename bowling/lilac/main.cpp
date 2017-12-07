#include"player.hpp"

int getBowl(int skittlesLeft);
int main(){
  int n;
  Player players[TURNS];
  std::cout << "Type the number of players in the game between 1 and 10 ";
  std::cin >> n;
  int turns = TURNS;
  std::string name;
  for (int i = 0; i < n; i++){
    std::cout << "Enter the name of player " << i + 1 << " ";
    std::cin >> name;
    players[i].setName(name);
  }

  for (int i = 0; i < turns; i++){
    std::cout << "++++++++++++++++++++++++++++++++++++" << std::endl;      
    std::cout << "Round " << i+1<< std::endl;      
    int skittlesLeft = 10;
    for (int j = 0; j < n; j++){
      std::cout << "-----------------------------------" << std::endl;      
      std::cout << players[j].getName() << "'s turn"<< std::endl;      
      std::cout << "first go" << std::endl;
      int b = getBowl(skittlesLeft);
      if (i==TURNS-1){
	players[j].makeBowl(b);
	if (b == 10){
	std::cout << "extra go" << std::endl;
	  skittlesLeft = 10;
	} else {
	std::cout << "second go" << std::endl;
	  skittlesLeft -= b;
	}

	b = getBowl(skittlesLeft);
	players[j].makeBowl(b);
	skittlesLeft -= b;
	if (skittlesLeft == 0){
	  skittlesLeft = 10;
	} else {
	  break;
	}
	std::cout << "extra go" << std::endl;
	b = getBowl(skittlesLeft);
	players[j].makeBowl(b);

	break;
      }

      if (players[j].makeBowl(b)){

	continue;
      } else {
	std::cout << "second go" << std::endl;
	skittlesLeft -= b;
	b = getBowl(skittlesLeft);
	players[j].makeBowl(b);
      }
      skittlesLeft = 10;
      players[j].display(); 
    }

  }
  std::cout << "GAME FINISHED" << std::endl;
  for (int j = 0; j < n; j++){
    players[j].display(); 
  }
  return 0;
}
int getBowl(int skittlesLeft){
  int b = -1;
  while (b < 0){
    std::cout << "Type a number to bowl between 0 to " << skittlesLeft << " inclusive ";
    std::cin >> b;
    if (cin.fail() || b < 0 || b > skittlesLeft){
      std::cin.clear();
      std::cin.ignore();
      
      b = -1;
      
      std::cout <<std::endl<< "incorrect input" << std::endl;

    }
  }
  return b;
}
