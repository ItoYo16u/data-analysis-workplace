# python data analysis env

## required
- ubuntu 18.x /20.x
- cuda
- pyenv
- Pandoc
- node v14.16.0 and npm
- R
- coursier(cs)


### install pyenv
```baash
sudo apt install -y \
build-essential \
libffi-dev \
libssl-dev \
zlib1g-dev \
liblzma-dev \
libbz2-dev \
libreadline-dev \
libsqlite3-dev

git clone https://github.com/pyenv/pyenv.git ~/.pyenv
git clone git://github.com/yyuu/pyenv-update.git ~/.pyenv/plugins/pyenv-update
```

### install pandoc

```bash
sudo apt install texlive-lang-japanese
sudo apt install texlive-xetex
sudo apt install pandoc pandoc-citeproc
```

### install node with nodebrew
```bash
curl -L git.io/nodebrew | perl - setup
# export path/to/nodebrew in ~/.(ba|z)shrc
nodebrew install v14.16.0
nodebrew use v14.16.0
```

### install R

```bash

```bash
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys E298A3A825C0D65DFD57CBB651716619E084DAB9
sudo add-apt-repository  'deb https://cloud.r-project.org/bin/linux/ubuntu bionic-cran40/'
sudo apt update && sudo apt upgrade
sudo apt install r-base
sudo apt install build-essential libcurl4-gnutls-dev libxml2-dev libssl-dev
# ここから下は jupyter インストール後
R
```

```R
install.packages(c('repr', 'IRdisplay', 'pbdZMQ', 'devtools'))
install.packages("plotly")
devtools::install_github('IRkernel/IRkernel')
IRkernel::installspec()
```

### install scala kernel

```bash
cs launch almond --scala 2.13.4 -- --install --display-name "Scala (2.13)"

```

## getting started

```bash
git clone path/to/repository
cd repository
pyenv local 3.9.2
pip install --upgrade pip
python -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
jupyter labextension install @krassowski/jupyterlab-lsp
jupyter labextension install jupyterlab-plotly
jupyter labextension install @jupyter-widgets/jupyterlab-manager plotlywidget
jupyter lab --generate-config
sed -i -e 's/# c.ServerApp.use_redirect_file = True/c.ServerApp.use_redirect_file = False/g' ~/.jupyter/jupyter_lab_config.py
grep use_redirect_file ~/.jupyter/jupyter_lab_config.py
cp ./metals-ls.json ./.venv/etc/jupyter/jupyter_server_config.d/metals-ls.json
cp ./jupyter_lab_config.json ~/.jupyter/jupyter_lab_config.json

jupyter lab
```
