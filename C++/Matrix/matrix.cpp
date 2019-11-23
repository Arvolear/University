#include "matrix.hpp"

template < typename K > template < typename T >
void Matrix<K>::LinkedList<T>::insert(LinkedList<T>::Node* node)
{
    if (!this->head)
    {
        this->head = this->tail = node;    
        this->amount++;
    }
    else if (node->index < this->head->index)
    {
        node->next = this->head;
        this->head = node;
        this->amount++;
    }
    else if (node->index > this->tail->index)
    {
        this->tail->next = node;
        this->tail = node;
        this->amount++;
    }
    else
    {
        typename LinkedList<T>::Node* curr = this->head;

        while (curr->next && curr->next->index <= node->index)
        {
            curr = curr->next;
        }
    
        if (curr->index == node->index)
        {
            curr->data += node->data;

            /*if (!curr->data)
              {
            //delete
            }*/
        }
        else
        {
            node->next = curr->next;
            curr->next = node;
            this->amount++;
        }
    }
}

template < typename K > template < typename T >
Matrix<K>::LinkedList<T>& Matrix<K>::LinkedList<T>::operator+=(const LinkedList<T>& ll)
{
    typename LinkedList<T>::Node* one = this->head;
    typename LinkedList<T>::Node* two = ll.head;

    LinkedList<T>* res = new LinkedList<T>();

    while (one && two)
    {
        if (one->index > two->index)
        {
            typename LinkedList<T>::Node* node = new typename LinkedList<T>::Node(two->data, two->index);

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
        else if (one->index == two->index)
        {
            one->data += two->data;

            if (!(one->data == T()))
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
                typename LinkedList<T>::Node* tmp = one;
                one = one->next;
                delete tmp;

                res->amount--;
            }

            two = two->next;

            continue;
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
            res->head = res->tail = new typename LinkedList<T>::Node(two->data, two->index);
        }
        else
        {
            res->tail->next = new typename LinkedList<T>::Node(two->data, two->index);
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

template < typename K > template < typename T >
Matrix<K>::LinkedList<T>& Matrix<K>::LinkedList<T>::operator-=(const LinkedList<T>& ll)
{
    typename LinkedList<T>::Node* one = this->head;
    typename LinkedList<T>::Node* two = ll.head;

    LinkedList<T>* res = new LinkedList<T>();

    while (one && two)
    {
        if (one->index > two->index)
        {
            typename LinkedList<T>::Node* node = new typename LinkedList<T>::Node(two->data, two->index);

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
        else if (one->index == two->index)
        {
            one->data -= two->data;

            if (!(one->data == T()))
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
                typename LinkedList<T>::Node* tmp = one;
                one = one->next;
                delete tmp;

                res->amount--;
            }

            two = two->next;

            continue;
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
            res->head = res->tail = new typename LinkedList<T>::Node(two->data, two->index);
        }
        else
        {
            res->tail->next = new typename LinkedList<T>::Node(two->data, two->index);
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

template < typename K > template < typename T >
Matrix<K>::LinkedList<T>& Matrix<K>::LinkedList<T>::operator*=(const LinkedList<T>& ll)
{
    typename LinkedList<T>::Node* one = this->head;
    typename LinkedList<T>::Node* two = ll.head;

    LinkedList<T>* res = new LinkedList<T>();

    while (one && two)
    {
        if (one->index > two->index)
        {
            two = two->next;
        }
        else if (one->index == two->index)
        {
            one->data *= two->data;

            if (!(one->data == T()))
            {
                if (!res->head)
                {
                    res->head = res->tail = new typename LinkedList<T>::Node(one->data);
                    res->amount++;
                }
                else
                {
                    res->head->data += one->data;

                    if (res->head->data == T())
                    {
                        //delete
                    }
                }
            }

            one = one->next;
            two = two->next;
        }
        else
        {
            one = one->next;
        }
    }

    if (res->head && res->head->data == T())
    {
        delete res->head;
        res->head = res->tail = nullptr;
        res->amount = 0;
    }

    clear();

    this->head = res->head;
    this->tail = res->tail;
    this->amount = res->amount;

    res->head = nullptr;
    delete res;

    return *this; 
}

/************ MATRIX ************/ 

template < typename K >
Matrix<K>::Matrix(int height, int width)
{
    this->height = height;
    this->width = width;
}

template < typename K >
Matrix<K>::Matrix(const Matrix<K>& matrix)
{
    this->height = matrix.height;
    this->width = matrix.width;

    typename LinkedList<LinkedList<K>>::Node* tmp = matrix.matrix.head;

    while (tmp)
    {
        typename LinkedList<LinkedList<K>>::Node* node = new typename LinkedList<LinkedList<K>>::Node(tmp->data, tmp->index);

        if(!this->matrix.head)
        {
            this->matrix.head = this->matrix.tail = node;
        }
        else
        {
            this->matrix.tail->next = node;
            this->matrix.tail = node;
        }

        tmp = tmp->next;
    }
}

template < typename K >
Matrix<K>& Matrix<K>::operator=(const Matrix& matrix)
{
    clear();

    this->height = matrix.height;
    this->width = matrix.width;

    typename LinkedList<LinkedList<K>>::Node* tmp = matrix.matrix.head;

    while (tmp)
    {
        typename LinkedList<LinkedList<K>>::Node* node = new typename LinkedList<LinkedList<K>>::Node(tmp->data, tmp->index);

        if(!this->matrix.head)
        {
            this->matrix.head = this->matrix.tail = node;
        }
        else
        {
            this->matrix.tail->next = node;
            this->matrix.tail = node;
        }

        tmp = tmp->next;
    }

    return *this;
}

template < typename K >
void Matrix<K>::insert(K data, int row, int col)
{
    if (row >= height || col >= width)
    {
        return;
    }

    typename LinkedList<K>::Node* smallNode = new typename LinkedList<K>::Node(data, col);

    LinkedList<K> ll;
    ll.insert(smallNode);

    typename LinkedList<LinkedList<K>>::Node* bigNode = new typename LinkedList<LinkedList<K>>::Node(ll, row);

    matrix.insert(bigNode); 
}

template < typename K >
Matrix<K>& Matrix<K>::operator+=(const Matrix<K>& matrix)
{
    if (height != matrix.height || width != matrix.width)
    {
        return *this;
    }

    this->matrix += matrix.matrix;

    return *this;
}

template < typename K >
Matrix<K>& Matrix<K>::operator-=(const Matrix<K>& matrix)
{
    if (height != matrix.height || width != matrix.width)
    {
        return *this;
    }
   
    this->matrix -= matrix.matrix;

    return *this;
}

template < typename K>
Matrix<K>& Matrix<K>::operator*=(const Matrix<K>& matrix)
{
    if (this->width != matrix.height)
    {
        return *this;
    }

    Matrix<K> mat = matrix.transpose();

    typename LinkedList<LinkedList<K>>::Node* one = this->matrix.head;

    Matrix<K>* res = new Matrix<K>(this->height, matrix.width);

    while (one)
    {
        Matrix<K>* help = new Matrix<K>(this->height, matrix.width);
        typename LinkedList<LinkedList<K>>::Node* two = mat.matrix.head;

        while (two)
        {
            LinkedList<K> tmp = one->data;
            tmp *= two->data;

            if (tmp.head)
            {
                help->insert(tmp.head->data, one->index, two->index);
            }

            two = two->next;
        }

        if (help->matrix.head)
        {
            (*res) += (*help);
        }

        delete help;
        one = one->next;
    }

    clear();

    this->height = res->height;
    this->width = res->width;
    this->matrix.head = res->matrix.head;
    this->matrix.tail = res->matrix.tail;
    this->matrix.amount = res->matrix.amount;

    res->matrix.head = nullptr;
    delete res;

    return *this;
}
        
template < typename K >
Matrix<K>& Matrix<K>::operator*=(const K& elem)
{
    typename LinkedList<LinkedList<K>>::Node* bigCurr = this->matrix.head;

    while (bigCurr)
    {
        typename Matrix<K>::template LinkedList<K>::Node* smallCurr = bigCurr->data.head;

        while (smallCurr)
        {
            smallCurr->data *= elem;

            smallCurr = smallCurr->next;
        }

        bigCurr = bigCurr->next;
    }

    return *this; 
}

template < typename K >
Matrix<K>& Matrix<K>::operator/=(const K& elem)
{
    typename LinkedList<LinkedList<K>>::Node* bigCurr = this->matrix.head;

    while (bigCurr)
    {
        typename Matrix<K>::template LinkedList<K>::Node* smallCurr = bigCurr->data.head;

        while (smallCurr)
        {
            smallCurr->data /= elem;

            smallCurr = smallCurr->next;
        }

        bigCurr = bigCurr->next;
    }

    return *this; 
}
        
template < typename P >
Matrix<P> operator+(const Matrix<P>& matrix0, const Matrix<P>& matrix1)
{
    Matrix<P> tmp = matrix0;

    return tmp += matrix1;
}
        
template < typename P >
Matrix<P> operator-(const Matrix<P>& matrix0, const Matrix<P>& matrix1)
{
    Matrix<P> tmp = matrix0;

    return tmp -= matrix1;
}
        
template < typename P >
Matrix<P> operator*(const Matrix<P>& matrix0, const Matrix<P>& matrix1)
{
    Matrix<P> tmp = matrix0;

    return tmp *= matrix1; 
}

template < typename K >
Matrix<K> Matrix<K>::transpose() const
{
    typename LinkedList<LinkedList<K>>::Node* bigCurr = this->matrix.head;

    /* swapped width & height */
    Matrix<K> res(width, height);

    while (bigCurr)
    {
        typename Matrix<K>::template LinkedList<K>::Node* smallCurr = bigCurr->data.head;

        while (smallCurr)
        {
            /* swapped col & row */
            res.insert(smallCurr->data, smallCurr->index, bigCurr->index);

            smallCurr = smallCurr->next;
        }

        bigCurr = bigCurr->next;
    }

    return res;
}

template < typename K >
int Matrix<K>::getHeight() const
{
    return height;
}

template < typename K >
int Matrix<K>::getWidth() const
{
    return width;
}

template < typename P >
ostream& operator<<(ostream& out, const Matrix<P>& matrix)
{
    typename Matrix<P>::template LinkedList<typename Matrix<P>::template LinkedList<P>>::Node* bigCurr = matrix.matrix.head;

    while (bigCurr)
    { 
        typename Matrix<P>::template LinkedList<P>::Node* smallCurr = bigCurr->data.head;

        out << "[" << bigCurr->index << "] :\n";

        while (smallCurr)
        {
            out << "(" << smallCurr->index << ")" << smallCurr->data << " "; 
            smallCurr = smallCurr->next;
        }

        out << endl;
        bigCurr = bigCurr->next;
    }

    return out;
}

template < typename K >
void Matrix<K>::clear()
{
    typename LinkedList<LinkedList<K>>::Node* curr = matrix.head;

    while (curr)
    {
        typename LinkedList<LinkedList<K>>::Node* tmp = curr;
        curr = curr->next; 
        delete tmp;
    }

    matrix.head = matrix.tail = nullptr;
    matrix.amount = 0;
}

template < typename K >
Matrix<K>::~Matrix() 
{
    clear();
}
