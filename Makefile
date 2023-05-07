.phony: repl install run

repl:
	clj -M:dev -e "(require 'cloped.dev)(in-ns 'cloped.dev)" -r

install:
	clj -P

run:
	clj -X:api
