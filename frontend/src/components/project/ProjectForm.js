import styles from "./ProjectForm.module.css";
import Input from "../form/Input";
import Select from "../form/Select";
import SubmitButton from "../form/SubmitButton";
import { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";
import Message from "../layout/Message";

function ProjectForm({ handleSubmit, btnText, projectData }) {
  const [categorias, setCategorias] = useState([]);
  const [projeto, setProjeto] = useState(projectData || {});

  const location = useLocation();
  let message = "";
  if (location.state && location.state.message) {
    message = location.state.message;
  }

  useEffect(() => {
    fetch("http://localhost:8080/category/all", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((resp) => resp.json())
      .then((data) => setCategorias(data));
  }, []);

  const submit = (e) => {
    e.preventDefault();
    handleSubmit(projeto);
  };

  function handleChange(e) {
    setProjeto({ ...projeto, [e.target.name]: e.target.value });
  }
  function handleCategory(e) {
    setProjeto({
      ...projeto,
      categoria: {
        id: e.target.value,
        name: e.target.options[e.target.selectedIndex].text,
      },
    });
  }
  return (
    <form onSubmit={submit} className={styles.form}>
      <Input
        type="text"
        text="Nome do Projeto"
        name="name"
        placeholder="Insira o nome do projeto"
        handleOnChange={handleChange}
        value={projeto.name ? projeto.name : ""}
      />
      <Input
        type="number"
        text="Orcamento"
        name="orcamento"
        placeholder="Insira o orcamento total"
        handleOnChange={handleChange}
        value={projeto.orcamento ? projeto.orcamento : ""}
      />
      <Select
        name="category_id"
        text="Selecione a categoria"
        options={categorias}
        handleOnChange={handleCategory}
        value={projeto.categoria ? projeto.categoria.id : ""}
      />
      <SubmitButton text={btnText} />
      {message && <Message msg={message} type="error" />}
    </form>
  );
}

export default ProjectForm;
