"use client";
import React, { useState } from "react";
//
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
//
const page = () => {
	const [Task, setTask] = useState("");
	const [Desc, setDesc] = useState("");
	const [Arr, setArr] = useState([]);

	var submitHandler = (e) => {
		e.preventDefault();
		setArr([...Arr, { Task, Desc }]);
		setTask("");
		setDesc("");
		// console.log(Arr);
		toast.success("🦄 Task Added", {
			position: "top-left",
			autoClose: 1000,
			hideProgressBar: false,
			closeOnClick: true,
			pauseOnHover: true,
			draggable: true,
			progress: undefined,
			theme: "light",
		});
	};

	let deleteHandler = (i, x) => {
		var arr2 = [...Arr];
		arr2.splice(i, 1);
		setArr(arr2);
		// console.log(Arr, arr2, i);
		if (x == 1) {
			toast.success("🦄 Task Completed!!!", {
				position: "top-right",
				autoClose: 1000,
				hideProgressBar: false,
				closeOnClick: true,
				pauseOnHover: true,
				draggable: true,
				progress: undefined,
				theme: "colored",
			});
		}
	};

	let editHandler = (i) => {
		setTask(`${Arr[i].Task}`);
		setDesc(`${Arr[i].Desc}`);
		deleteHandler(i, 0);
	};

	let items = <li>Nothing here</li>;
	if (Arr.length) {
		items = Arr.map((e, k) => {
			return (
				<li
					key={k}
					className="flex items-center justify-between w-2/2 mb-8"
				>
					<h2 className="text-3xl">{e.Task}</h2>
					<h3 className="text-1xl">{e.Desc}</h3>
					<div id="btns">
						<button
							onClick={() => {
								editHandler(k);
							}}
							className="bg-orange-500 rounded border-2 border-black px-4 py-2 mr-3"
						>
							Edit
						</button>
						<button
							onClick={() => {
								deleteHandler(k, 1);
							}}
							className="bg-orange-500 rounded border-2 border-black px-4 py-2"
						>
							Delete
						</button>
					</div>
				</li>
			);
		});
	}
	return (
		<>
			<ToastContainer />
			<h1 className="text-center bg-slate-400 font-serif font-bold text-white p-10 text-8xl border-4 border-green-600">
				React ToDo App
			</h1>
			<form onSubmit={submitHandler} className="ml-6">
				<input
					type="text"
					placeholder="Enter Tasks"
					className="text-2xl border-zinc-800 border-4 m-4 px-4 py-2"
					value={Task}
					onChange={(e) => {
						setTask(e.target.value);
					}}
				/>
				<input
					type="text"
					placeholder="Enter Description Here"
					className="text-2xl border-zinc-800 border-4 m-4 px-4 py-2"
					value={Desc}
					onChange={(e) => {
						setDesc(e.target.value);
					}}
				/>
				<button
					className="bg-black text-white px-4 py-3 m-6 rounded text-2xl font-bold"
					// onClick={sub}
				>
					Add Task
				</button>
			</form>
			<hr />
			<div id="list" className="bg-blue-200 w-2/3 mx-auto my-5 p-9">
				<h1
					id="task"
					className="font-extrabold  text-center text-6xl mb-5"
				>
					Tasks:
				</h1>
				<ul>{items}</ul>
			</div>
		</>
	);
};

export default page;
