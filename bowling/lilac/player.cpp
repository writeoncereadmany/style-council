#include"player.hpp"

Player::Player(){
  name = "Defualt";
  turn = 0;
  firstOrSecond = true;
}
void addFrame(round& y, void* x) {
  int u = *(int*)x;
  y.addFrame(u);
}

void Player::setName(std::string aName){
  name = aName;
}
void Player::removeDoneFromList(){  
  while(!activeQueue.isEmpty() && activeQueue.getDataAtStart().isDone){
    activeQueue.deleteNodeAtStart();
  }
}

void Player::addFrameToDone(const int& frame){
  int f = frame;
  activeQueue.implementFunctionOnListItems(addFrame, &f);
}

std::string Player::getName(){
  return name;
}

int Player::getScore(){
  int score = 0;
  for (int i = 0; i < TURNS; i++){
    score += scores[i].getFinalScore();
  }
  return score;
}

bool Player::makeBowl(const int& score){
  if (turn == TURNS ){
    if (!activeQueue.isEmpty()) {
      removeDoneFromList();
      addFrameToDone(score);
      return true;
    } else {
      return false;
    }
  } 

  if (firstOrSecond && score ==10){
    activeQueue.insertNodeAtEnd(scores[turn]);
    firstOrSecond = true;
    turn++;
    
  } else if (firstOrSecond){
    activeQueue.insertNodeAtEnd(scores[turn]);
    firstOrSecond = false;
  } else {
    firstOrSecond = true;
    turn++;
  }
  removeDoneFromList();
  addFrameToDone(score);
  return(firstOrSecond);
}

void Player::display(){
  std::cout << "-" <<name << "'s scores-" << endl;

  for (int i = 0; i < TURNS; i++){
    std::cout << "======";
  }
  std::cout << std::endl;
  for (int i = 0; i < TURNS; i++){
    std::cout << "|"<<scores[i].getFirstBox() << "|" << scores[i].getSecondBox() << "||"; 
  }
  std::cout << std::endl;
  for (int i = 0; i < TURNS; i++){
    std::cout << "--V---";
  }
  std::cout << std::endl; 
  for (int i = 0; i < TURNS; i++){
    int n = scores[i].getFinalScore();
    
    if (n > TURNS-1){
      std::cout << "| ";
      if (i > 0) {
	std::cout << scores[i].getFinalScore() << "  " ; 
      } else {
	std::cout << scores[i].getFinalScore() << " " ; 

      }      
    } else {
      if (i > 0) {
	std::cout << "|  ";
      } else {
	std::cout << "| ";
      }
      std::cout << scores[i].getFinalScore() << "  " ; 
    }

  }
  std::cout << "|"<<std::endl;
  for (int i = 0; i < TURNS; i++){
    std::cout << "======";
  }
  std::cout << std::endl << name<<"'s total = " << getScore() << std::endl;  
}





