import styles from "../project/ProjectForm.module.css";
import Input from "../form/Input";
import SubmitButton from "../form/SubmitButton";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom";

const ServiceForm = ({ btnText, serviceData }) => {
  const [service, setService] = useState(serviceData || {});
  const { id } = useParams();
  const navigate = useNavigate();

  const submit = (e) => {
    e.preventDefault();
    console.log("serviceForm: " + JSON.stringify(service));
    createService(id, service);
  };
  function handleChange(e) {
    setService({ ...service, [e.target.name]: e.target.value });
    //console.log(service);
  }

  function createService(id, service) {
    console.log("2createService: " + JSON.stringify(service));
    console.log("id: " + id);
    fetch(`http://localhost:8080/projects/${id}/services/add`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(service),
    })
      .then((resp) => resp.json())
      .then((data) => {
        //setMessage("Serviço criado com Sucesso!");
        //setType("sucess");
        console.log(data);
        setService(data);
        navigate(`/projects/${id}`);
      });
  }

  return (
    <form onSubmit={submit} className={styles.form}>
      <Input
        type="text"
        text="Nome do Serviço"
        name="name"
        placeholder="Insira o nome do Serviço"
        handleOnChange={handleChange}
        value={service.name ? service.name : ""}
      />
      <Input
        type="number"
        text="Custo do Serviço"
        name="custo"
        placeholder="Insira o valor total"
        handleOnChange={handleChange}
        value={service.custo ? service.custo : ""}
      />
      <Input
        type="text"
        text="Descrição do Serviço"
        name="descricao"
        placeholder="Descreva o Serviço"
        handleOnChange={handleChange}
        value={service.descricao ? service.descricao : ""}
      />
      <SubmitButton text={btnText} />
    </form>
  );
};

export default ServiceForm;
