package metodos de ordenacao;

public class Filas {
    Celula<E> frente;
    Celula<E> tras;

    Fila(){     //criando uma fila
        Celula<E> sentinela = new Celula<E>();
        frente = tras = sentinela;
    }

    public boolean vazia(){
        return (frente==tras);
    }

    public void enfileirar(E item){
        Celula<E> novaCelula = new Celula<E>(item) // cria uma nova célula que contém o item
        tras.setProximo(novaCelula)     // insere a nova celula atras do ultimo
        tras.tras.getProximo();         // atualiza tras, agora tras é a nova celula
    }

    public E desenfileirar(){ //o método de desenfileirar na verdade tira o segundo da fila pq o primeiro elemento é o sentinela
        E item = null;
        Celula<E> primeiro;

        item = consultarPrimeiro(); // o consultarPrimeiro retorna o que vem dps da sentinela
        primeiro = frente.getProximo(); // frente é o sentinela, o primeiro passa a ser o q vem dps do sentinela
        frente.setProximo(primeiro.getProximo);  // pega o dps do sentinela e diz que na vdd ele fica é depois da sentinal, o que exclui o primeiro

        primeiro.setProximo(null);  // isso isola de vez o primeiro 

        if (primeiro == tras){
            tras = frente;
        }
    }

    public E consultarPrimeiro(){
        if(vazia()){
            throw ex
        }
        return frente.getProximo().getItem(); //isso pega o item que vem depois da sentinela
    }    
}

class Celula<T> {           // o tipo genérico T é um PONTEIRO para a próxima celula
    T item;                 //armazena o dado q a cel deve conter
    Celula<T> proximo;      // referencia a proxima celula

    public Celula(){        //método sem argumentos
        this.item = null;  
        setProximo(null);   //cria uma célula sentinela
    }

    public Celula(T item){   // cria uma celula com o dado ITEM
        this.item = item;
        setProximo(null);   //seta o proximo como nulo o que indica que a celula criada é a ultima
    }

    public Celula(T item, Celula<T> proximo){   
        this.item = item;       //cria nova cel
        this.proximo = proximo; //seta o proximo como proximo, o que especifica uma posição
    }

    public T getItem(){
        return item
    }

    public Celula<T> getProximo(){
        return proximo;
    }

    public void setProximo(){
        this.proximo = proximo
    }
}