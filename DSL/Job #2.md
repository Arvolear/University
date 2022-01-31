**Припустимо**, що контекстно-вільна граматика представляється словником
```
{'toks': set(token), 'vars': dict(var: definition), 'hvar': var}
token : (class, value)
class : int
value : str
var : str                 # імєя нетермінала
definition : list(rule)
rule : list(var | token)  # права частина правила
```
**Необхідно** написати функції для:

1. прибирання сторонніх нетерміналів
2. визначення зникаючих символів
