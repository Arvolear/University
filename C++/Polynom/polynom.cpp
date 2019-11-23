#include "polynom.hpp"

template < typename T >
Polynom<T>::Polynom()
{
    head = tail = nullptr;
    this->amount = 0;
}

template < typename T >
Polynom<T>::Polynom(const Polynom<T>& poly)
{
    head = tail = nullptr;
    
    Node* curr = poly.head;
    this->amount = 0;

    while (curr)
    {
        insert(curr->coeff, curr->power);
        curr = curr->next;
    }
}
        
template < typename T >
Polynom<T>& Polynom<T>::operator=(const Polynom<T>& poly)
{
    clear();
    
    Node* curr = poly.head;

    while (curr)
    {
        insert(curr->coeff, curr->power);
        curr = curr->next;
    }

    return *this;
}

template < typename T >
void Polynom<T>::insert(T coeff, unsigned int power, bool replace)
{
    Node* node = new Node(coeff, power);
    
    if (!amount)
    {
        head = tail = node;
    }
    else if (power > head->power)
    {
        node->next = head;
        head = node;
    }
    else if (power < tail->power)
    {
        tail->next = node;
        tail = tail->next;
    }
    else
    {
        Node* curr = head;
        Node* prev = head;

        while (curr->next && power <= curr->next->power)
        {
            prev = curr;
            curr = curr->next;
        }

        if (power == curr->power)
        {
            if (replace)
            {
                curr->coeff = coeff;
            }
            else
            {
                curr->coeff += coeff;

                if (!curr->coeff)
                {
                    prev->next = curr->next;

                    amount--;
                    delete curr;
                }
            }

            return;
        }
        else
        {
            node->next = curr->next;
            curr->next = node;
        }
    }

    amount++;
}

template < typename T >
void Polynom<T>::remove(unsigned int power)
{
    if (!amount)
    {
        return;
    }

    if (head == tail && head->power == power)
    {
        delete head;
        head = tail = nullptr;
        amount--;

        return;
    }

    Node* prev = head;
    Node* curr = head;

    while (curr && curr->power != power)
    {
        prev = curr;
        curr = curr->next;
    }

    if (curr)
    {
        if (curr == head)
        {
            head = curr->next;
        }

        if (curr == tail)
        {
            tail = prev;
        }

        prev->next = curr->next;
        delete curr;
        amount--;
    }
}

/* merge + add */
template < typename T >
Polynom<T>& Polynom<T>::operator+=(const Polynom<T>& poly)
{
    Node* one = head;
    Node* two = poly.head;

    Polynom* res = new Polynom();

    while (one && two)
    {
        if (one->power < two->power)
        {
            Node* node = new Node(two->coeff, two->power);

            if (!res->head)
            {
                res->head = res->tail = node;
            }
            else
            {
                res->tail->next = node;
                res->tail = res->tail->next;
            }

            two = two->next;
        }
        else if (one->power == two->power)
        {
            one->coeff += two->coeff;

            if (one->coeff)
            {
                if (!res->head)
                {
                    res->head = res->tail = one;
                }
                else
                {
                    res->tail->next = one;
                    res->tail = res->tail->next;
                }

                one = one->next;
            }
            else
            {
                Node* tmp = one;
                one = one->next;
                delete tmp;
                res->amount--;
            }

            two = two->next;
        }
        else
        {
            if (!res->head)
            {
                res->head = res->tail = one;
            }
            else
            {
                res->tail->next = one;
                res->tail = res->tail->next;
            }

            one = one->next;
        }

        res->amount++;
    }

    while (one)
    {
        if (!res->head)
        {
            res->head = res->tail = one;
        }
        else
        {
            res->tail->next = one;
            res->tail = res->tail->next;
        }

        one = one->next;
        res->amount++;
    }

    while (two)
    {
        if (!res->head)
        {
            res->head = res->tail = new Node(two->coeff, two->power);
        }
        else
        {
            res->tail->next = new Node(two->coeff, two->power);
            res->tail = res->tail->next;
        }

        two = two->next;
        res->amount++;
    }

    this->head = res->head;
    this->tail = res->tail;
    this->amount = res->amount;

    res->head = nullptr;
    delete res;

    return *this;
}

template < typename T >
Polynom<T>& Polynom<T>::operator-=(const Polynom<T>& poly)
{
    Node* one = head;
    Node* two = poly.head;

    Polynom* res = new Polynom();

    while (one && two)
    {
        if (one->power < two->power)
        {
            Node* node = new Node(-two->coeff, two->power);

            if (!res->head)
            {
                res->head = res->tail = node;
            }
            else
            {
                res->tail->next = node;
                res->tail = res->tail->next;
            }

            two = two->next;
        }
        else if (one->power == two->power)
        {
            one->coeff -= two->coeff;

            if (one->coeff)
            {
                if (!res->head)
                {
                    res->head = res->tail = one;
                }
                else
                {
                    res->tail->next = one;
                    res->tail = res->tail->next;
                }

                one = one->next;
            }
            else
            {
                Node* tmp = one;
                one = one->next;
                delete tmp;
                res->amount--;
            }

            two = two->next;
        }
        else
        {
            if (!res->head)
            {
                res->head = res->tail = one;
            }
            else
            {
                res->tail->next = one;
                res->tail = res->tail->next;
            }

            one = one->next;
        }

        res->amount++;
    }

    while (one)
    {
        if (!res->head)
        {
            res->head = res->tail = one;
        }
        else
        {
            res->tail->next = one;
            res->tail = res->tail->next;
        }

        one = one->next;
        res->amount++;
    }

    while (two)
    {
        if (!res->head)
        {
            res->head = res->tail = new Node(-two->coeff, two->power);
        }
        else
        {
            res->tail->next = new Node(-two->coeff, two->power);
            res->tail = res->tail->next;
        }

        two = two->next;
        res->amount++;
    }

    this->head = res->head;
    this->tail = res->tail;
    this->amount = res->amount;

    res->head = nullptr;
    delete res;

    return *this;
}

template < typename T >
Polynom<T>& Polynom<T>::operator*=(const Polynom<T>& poly)
{
    Node* one = head;

    Polynom* res = new Polynom();

    while (one)
    {
        Polynom* tmp = new Polynom();
        Node* two = poly.head;

        while (two)
        {
            tmp->insert(one->coeff * two->coeff, one->power + two->power, false);
            two = two->next;
        }

        (*res) += (*tmp);

        one = one->next;
        delete tmp;
    }

    clear();

    this->head = res->head;
    this->tail = res->tail;
    this->amount = res->amount;

    res->head = nullptr;
    delete res;

    return *this;
}

template < typename T >
Polynom<T>& Polynom<T>::operator/=(const Polynom<T>& poly)
{
    Polynom* res = new Polynom();

    while (true)
    {
        Node* one = head;
        Node* two = poly.head;

        if (!one || !two || int(one->power - two->power) < 0)
        {
            break;
        }

        Polynom* localRes = new Polynom();

        res->insert(one->coeff / two->coeff, one->power - two->power, false);
        localRes->insert(one->coeff / two->coeff, one->power - two->power, false);

        (*localRes) *= poly; 
        (*this) -= (*localRes);

        delete localRes;
    }

    clear();

    this->head = res->head;
    this->tail = res->tail;
    this->amount = res->amount;

    res->head = nullptr;
    delete res;

    return *this;
}

template < typename P >
Polynom<P> operator+(const Polynom<P>& poly0, const Polynom<P>& poly1)
{
    Polynom<P> res = poly0;

    return res += poly1;
}

template < typename P >
Polynom<P> operator-(const Polynom<P>& poly0, const Polynom<P>& poly1)
{
    Polynom<P> res = poly0;

    return res -= poly1;
}

template < typename P >
Polynom<P> operator*(const Polynom<P>& poly0, const Polynom<P>& poly1)
{
    Polynom<P> res = poly0;

    return res *= poly1;
}

template < typename P >
Polynom<P> operator/(const Polynom<P>& poly0, const Polynom<P>& poly1)
{
    Polynom<P> res = poly0;

    return res /= poly1;
}

template < typename P >
void Polynom<P>::derivate(unsigned int steps)
{
    for (unsigned int i = 0; i < steps; i++)
    {
        Node* prev = head;
        Node* curr = head;

        while (curr)
        {
            /* happens only to the tail */
            if (int(curr->power - 1) < 0)
            {
                prev->next = nullptr; 
                amount--;
                delete tail;
            }

            curr->coeff *= curr->power;
            curr->power--;

            prev = curr;
            curr = curr->next;
        }
    }
}

template < typename P >
size_t Polynom<P>::size() const
{
    return amount;
}

template < typename P >
int Polynom<P>::power() const
{
    return abs(head->power);
}

template < typename P >
ostream& operator<<(ostream& out, const Polynom<P>& poly)
{
    typename Polynom<P>::Node* curr = poly.head;

    if (!curr)
    {
        out << "Empty!";
    }

    while (curr)
    {
        out << curr->coeff << "x^" << curr->power;

        if (curr->next)
        {
            out << " + ";
        }

        curr = curr->next;
    }

    return out;
}

template < typename T >
void Polynom<T>::clear()
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

template < typename T >
Polynom<T>::~Polynom()
{
    clear();
}
