#pragma once
#include<iostream>
using namespace std;
template <typename T> struct node {
  T* data;
  node<T> *next;
};


template <typename T> class linkedList {

private:
  node<T> *head, *tail;
public:
  linkedList();
  bool isEmpty();
  void insertNodeAtEnd(T& value);
  void deleteNodeAtStart();
  T getDataAtStart();
  void implementFunctionOnListItems(void (*f)(T&, void*) , void*);
  ~linkedList();
  
};

template<typename T>
linkedList<T>::linkedList(){
  head=0;
  tail=0;
}

template<typename T>
void linkedList<T>::insertNodeAtEnd(T& value){
  node<T> *temp=new node<T>;
  temp->data=&value;
  temp->next=0;
  if(head==0){
    head=temp;
    tail=temp;
    temp=0;
  }
  else {
    tail->next=temp;
    tail=temp;
  }
}

template<typename T>
void linkedList<T>::deleteNodeAtStart() {
  node<T> *temp=new node<T>;
  temp=head;
  head=head->next;
  delete temp;
}

template<typename T>
linkedList<T>::~linkedList(){
  node<T>* current = head;
  while( current != 0 ) {
    node<T>* next = current->next;
    delete current;
    current = next;
  }
  head = 0;
}

template<typename T>
T linkedList<T>::getDataAtStart(){
  node<T> *n = head;
  T t = *(n->data);
  return t;
}

template<typename T>
bool linkedList<T>::isEmpty(){
  return head == 0;
}

template<typename T>
void linkedList<T>::implementFunctionOnListItems(void (*f)(T&, void*) , void* t){
  if (isEmpty()){
    return;
  }

  node<T>* current = head;
  while( current != 0 ) {
    node<T>* next = current->next;
    (*f)(*(current->data), t);
    current = next;
  }

  
}
