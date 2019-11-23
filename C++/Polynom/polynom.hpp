#pragma once

#include <iostream>
#include <algorithm>

using namespace std;

template < typename T >
class Polynom
{
    private:
        struct Node
        {
            T coeff;
            unsigned int power;

            Node* next;

            Node()
            {
                this->coeff = 0.0;
                this->power = 0;
                this->next = nullptr;
            }

            Node(T coeff, unsigned int power)
            {
                this->coeff = coeff;
                this->power = power;
                this->next = nullptr;
            }
        };

        Node* head;
        Node* tail;
        size_t amount;

    public:
        Polynom();
        Polynom(const Polynom& poly);
        Polynom& operator=(const Polynom& poly);

        void insert(T coeff, unsigned int power, bool replace = true);
        void remove(unsigned int power);

        Polynom& operator+=(const Polynom& poly);
        Polynom& operator-=(const Polynom& poly);
        Polynom& operator*=(const Polynom& poly);
        Polynom& operator/=(const Polynom& poly);

        template < typename P >
        friend Polynom<P> operator+(const Polynom<P>& poly0, const Polynom<P>& poly1);
        template < typename P >
        friend Polynom<P> operator-(const Polynom<P>& poly0, const Polynom<P>& poly1);
        template < typename P >
        friend Polynom<P> operator*(const Polynom<P>& poly0, const Polynom<P>& poly1);
        template < typename P >
        friend Polynom<P> operator/(const Polynom<P>& poly0, const Polynom<P>& poly1);

        void derivate(unsigned int steps);

        size_t size() const;
        int power() const;

        template < typename P >
        friend ostream& operator<<(ostream& out, const Polynom<P>& poly);

        void clear();

        ~Polynom();
};
