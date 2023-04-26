import styles from "../project/ProjectCard.module.css";

const ServiceCard = ({ id, name, custo, descricao, handleRemove }) => {
  return (
    <div className={styles.project_card}>
      <p>aaa</p>
      <h4>{name}</h4>
      <p>
        <span>Custo Total: </span>R${custo}
      </p>
      <p>{descricao}</p>
    </div>
  );
};

export default ServiceCard;
