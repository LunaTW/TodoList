## Why need DTO

#### Question: 为什么需要dto？

|Dto|Model|
|---|---|
| DTO是面向界面UI，是通过UI的需求来定义的。|Model面向业务，我们是通过业务来定义Model的。|

✨通过DTO我们实现了表现层与Model之间的解耦，表现层不引用Model，如果开发过程中我们的模型改变了，而界面没变，我们就只需要改Model而不需要去改表现层中的东西。