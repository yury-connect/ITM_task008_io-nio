package task1811;

/* 
Wrapper (Decorator)
Разберись, что делает программа.
Аналогично классу DecoratorRunnableImpl создай класс DecoratorMyRunnableImpl.
Вывод в консоль должен быть таким:
DecoratorRunnableImpl body
DecoratorMyRunnableImpl body
RunnableImpl body


Requirements:
1. Создай класс DecoratorMyRunnableImpl, аналогичный DecoratorRunnableImpl.
2. После запуска, каждый класс должен вывести в консоль "<свое имя класса> body", например "DecoratorRunnableImpl body".
3. Классы RunnableImpl и DecoratorRunnableImpl изменять нельзя.
4. Метод main изменять нельзя.*/

public class Solution {

    public static void main(String[] args) {
        new Thread(new DecoratorRunnableImpl(new DecoratorMyRunnableImpl(new RunnableImpl()))).start();
    }

    public static class RunnableImpl implements Runnable {
        @Override
        public void run() {
            System.out.println("RunnableImpl body");
        }
    }

    public static class DecoratorRunnableImpl implements Runnable {
        private Runnable component;

        public DecoratorRunnableImpl(Runnable component) {
            this.component = component;
        }

        @Override
        public void run() {
            System.out.println("DecoratorRunnableImpl body");
            component.run();
        }
    }

    // мой реализуемый класс
    public static class DecoratorMyRunnableImpl implements Runnable {
        private Runnable component;

        public DecoratorMyRunnableImpl(RunnableImpl runnable) {
            this.component = runnable;
        }

        @Override
        public void run() {
            System.out.println("DecoratorMyRunnableImpl body");
            component.run();
        }
    }
}



/*
В Java Decorator (декоратор) — это структурный шаблон проектирования, который позволяет динамически добавлять объектам
новые обязанности, оборачивая их в классы-декораторы. Это полезно, когда нужно расширить поведение объекта, не изменяя
его код напрямую и не затрагивая другие объекты того же класса.

Основная идея:
Объект оборачивается в декоратор, который имеет тот же интерфейс, что и сам объект.
Декоратор добавляет новое поведение, но при этом может вызывать методы
оригинального объекта, чтобы сохранить его исходное поведение.
 */