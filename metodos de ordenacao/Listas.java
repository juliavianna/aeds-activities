package metodos de ordenacao;

public class Listas {
    Celula<E> primeiro;
    Celula<E> ultimo;
    int tamanho

    public Lista(){
        Celula<E> sentinela = new Celula<>();

        thhis.primeiro = this.ultimo = sentinela;
        this.tamanho = 0;
    }

    public boolean vazia(){
        return (this.primeiro == this.ultimo)
    }

    public void inserir(E novo, int posicao){
        Celula<E> anterior, novCelula, proximaCelula;
        
        if((posicao < 0)|| (posicao > this.tamanho)){
            throw ex
        }

        anterior = this.primeiro;  //seta o anterior como sendo a primeira celula
        for (int i = 0; i<posicao; i++){  //percorre a lista toda, até a posição desejada
            anterior = anterior.getProximo();   //vai atualizando a var anterior pra ir pegando os proximos e assim ir percorrendo a lista
        }
        novaCelula = new Celula<>(novo); //passa o valor do item pra uma nova celula
        proximaCelula = anterior.getProximo();  // seta o valor da proxima 

        anterior.setProximo(novaCelula); //insere a nova celula depois da anterior => anterior á nova celula
        novaCelula.setProximo(proximaCelula) //proximaCelula fica depois da nova celula

        this.tamanho++; //incrementa o tamanho da fila
    }

    public E remover (int posicao){
        Celula<E> anterior, celulaRemovida, proximaCelula;
        if(vazia()){
            throw ex
        }
        if ((posicao<0) || (posicao>=this.tamanho)){
            throw ex
        }
        anterior = this.primeiro;//seta o anterior como sendo a primeira celula
        for (int i = 0; i<posicao; i++){  //percorre a lista toda, até a posição desejada
            anterior = anterior.getProximo();   //vai atualizando a var anterior até pegar a anterior da celula q vai ser removida
        }
        celulaRemovida = anterior.getProximo // pega a celula a ser removida
        proximaCelula = celulaRemovida.getProximo(); //pega a celula q vem dps da a ser removida

        anterior.setProximo(proximaCelula) // isso exclui a celula a ser removida
        celulaRemovida.setProximo(null)  // isola a removida

        if (celulaRemovida == this.ultimo){
            this.ultimo = anterior
        }
        this.tamanho--;   //decrementa o tamanho

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
