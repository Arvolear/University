#pragma once

#include <iostream>
#include <algorithm>

using namespace std;

template < typename K >
class Matrix
{
    private:
        template < typename T >
        class LinkedList
        {
            public:
                struct Node
                {
                    T data;
                    Node* next;
                    int index;

                    Node()
                    {
                        next = nullptr;
                        index = -1;
                    }

                    Node(const T& data)
                    {
                        this->data = data;
                        next = nullptr;
                        index = -1;
                    }
                    
                    Node(const T& data, int index)
                    {
                        this->data = data;
                        next = nullptr;
                        this->index = index;
                    }
                };

                Node* head;
                Node* tail;

                size_t amount;

                LinkedList()
                {
                    head = tail = nullptr;
                    amount = 0;
                }

                LinkedList(const LinkedList& ll)
                {
                    head = tail = nullptr; 
                    amount = 0;
                    
                    Node* tmp = ll.head;

                    while (tmp)
                    {
                        Node* node = new Node(tmp->data, tmp->index);

                        if (!head)
                        {
                            head = tail = node;
                        }
                        else
                        {
                            tail->next = node;
                            tail = node;
                        }

                        tmp = tmp->next;
                    } 
                }

                LinkedList& operator=(const LinkedList& ll)
                {
                    clear();
                    Node* tmp = ll.head;

                    while (tmp)
                    {
                        Node* node = new Node(tmp->data, tmp->index);

                        if (!head)
                        {
                            head = tail = node;
                        }
                        else
                        {
                            tail->next = node;
                            tail = node;
                        }

                        tmp = tmp->next;
                    }

                    return *this;
                }

                bool operator==(const LinkedList<T>& ll)
                {
                    Node* one = head;
                    Node* two = ll.head;

                    while (one && two)
                    {
                        if (!(one->data == two->data))
                        {
                            return false;
                        }

                        one = one->next;
                        two = two->next;
                    }

                    if (one || two)
                    {
                        return false;
                    }
                    
                    return true;
                }
                
                void insert(Node* node);
                void remove(unsigned int col);

                LinkedList& operator+=(const LinkedList& ll);
                LinkedList& operator-=(const LinkedList& ll);
                LinkedList& operator*=(const LinkedList& ll);

                void clear()
                {
                    while (head)
                    {
                        Node* tmp = head;
                        head = head->next;
                        delete tmp;
                    }

                    head = tail = nullptr;
                    amount = 0; 
                }

                virtual ~LinkedList()
                {
                    clear();
                }
        };

        LinkedList < LinkedList < K > > matrix;
        int height, width;

    public:
        Matrix(int height, int width);
        Matrix(const Matrix& matrix);
        Matrix& operator=(const Matrix& matrix);

        void insert(K data, int row, int col);
        void remove(int row, int col);

        Matrix& operator+=(const Matrix& matrix);
        Matrix& operator-=(const Matrix& matrix);
        Matrix& operator*=(const Matrix& matrix);

        template < typename P >
        friend Matrix<P> operator+(const Matrix<P>& matrix0, const Matrix<P>& matrix1);
        template < typename P >
        friend Matrix<P> operator-(const Matrix<P>& matrix0, const Matrix<P>& matrix1);
        template < typename P >
        friend Matrix<P> operator*(const Matrix<P>& matrix0, const Matrix<P>& matrix1);
        
        Matrix& operator*=(const K& elem);
        Matrix& operator/=(const K& elem);
       
        Matrix transpose() const;

        int getHeight() const;
        int getWidth() const;

        template < typename P >
        friend ostream& operator<<(ostream& out, const Matrix<P>& matrix);

        void clear();

        ~Matrix();
};
