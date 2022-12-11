import os

from setuptools import setup, find_packages


def read(fname):
    with open(os.path.join(os.path.dirname(__file__), fname)) as f:
        return f.read()

from pathlib import Path

this_directory = Path(__file__).parent
long_description = (this_directory / "README.md").read_text()

setup(
    name='custos-sdk',
    long_description_content_type="text/markdown",
    version='1.0.18',
    packages=find_packages(),
    package_data={'': ['*.pem','*.md']},
    include_package_data=True,
    url='http://custos.com',
    license='Apache License 2.0',
    author='Custos Developers',
    author_email='dev@airavata.apache.org',
    install_requires=['google>=3.0.0', 'protobuf>=3.12.2',
                      'google-api-python-client>=1.10.0',
                      'googleapis-common-protos>=1.52.0',
                      'grpcio>=1.30.0',
                      'pyopenssl>=19.1.0',
                      'configparser>=5.0.0',
                      'requests>=2.13.0',
                      'requests-oauthlib>=0.7.0',
                      'urllib3>=1.25.9'],
    description='Apache Custos Python  SDK',
    long_description=long_description,
)
