FROM python:3.6

RUN groupadd -r uwsgi && useradd -r -g uwsgi uwsgi
RUN python -m pip install --upgrade pip 
RUN pip3 install Flask uwsgi
WORKDIR /app
COPY app /app
COPY cmd.sh /

EXPOSE 9090 9191
USER uwsgi

CMD ["/cmd.sh"]
# $ docker build -t identidock .
# $ docker run -d -P --name port-test identidock
# $ docker port port-test
# $ docker run -e "ENV=DEV" -p 5000:5000 identidock