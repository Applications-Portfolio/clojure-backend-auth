# cloped/clojure-backend-auth

# How this project was created

From scratch using https://github.com/seancorfield/deps-new running the following command:

```shell
clojure -Tnew app :name cloped/clojure-backend-auth :target-dir clojure-backend-auth :overwrite true
```

# Motivation

This is a project that shows how to use the following stack with clojure:

- Clojure CLI + Tools Deps
- Pedestal
- Component
- Clojure tests
- Datomic
- Buddy-auth

# How to run local with REPL

This is a project in java 17.

You have to run `clj -Mdev` so the REPL will be prompted on dev/user.clj file, this
will allow you add anything from your toolbox for local development with REPL, Morse is an example.

# Base of this project

The base of this project is the Pedestal +

# Some explanations

- Pedestal component startup
  - Is based on the guide of how to run pedestal with component http://pedestal.io/guides/pedestal-with-component.
- Pedestal hot reloading of routes controllers modification on REPL
  - To achieve pedestal hot reloading the doc of pedestal reloading on REPL was followed http://pedestal.io/guides/live-repl.
  - On code you may find on pedestal.clj the line where it runs `#(route/expand-routes (deref (var routes/routes)))` instead of just passing an array of routes. Doing this function is only recommended for local development.
  - Remember that for achieving this you need to add a ``` before your functions on routers list
- Pedestal hot reloading of adding routes on REPL
  - To achieve this is the same doc from above topic. Specifically on code is this part: `(deref (var routes/routes))`
- System restart from REPL
  - System restart uses 

# Installation

You can download dependencies with `clj -P`

## License

Copyright © 2023 Eduardocarvalho

Distributed under the Eclipse Public License version 1.0.
