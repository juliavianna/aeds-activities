import java.util.*

public class Pilhas {
    Celula<E> topo;
    Celula<E> fundo;

    public Pilha(){
        Celula<E> sentinela = new Celula<E>();
        fundo = topo = sentinela;
    }

    public boolean vazia(){
        return fundo == topo
    }

    public void empilhar(E item){
        topo = new Celula<E>(item, topo)  //a variável topo passa a ser a nova célula
    }

    public E desempilhar (){
        E desempilhado = consultarTopo();  //pega a celula a ser desempilhada
        topo = topo.getProximo; //como é uma pilha, o próximo do topo é o anterior, então o anterior passa a ser o topo
        return desempilhado     //retorna a celula q foi desempilhada
    }

    public E consultarTopo(){
        if(vazia()){
            throw ex
        }
        return topo.getItem
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
